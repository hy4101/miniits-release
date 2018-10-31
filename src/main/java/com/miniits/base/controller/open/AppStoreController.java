package com.miniits.base.controller.open;

import com.miniits.base.model.entity.AppContent;
import com.miniits.base.model.entity.AppStore;
import com.miniits.base.model.vo.AppStoresVO;
import com.miniits.base.model.vo.AppVO;
import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.AppStoreServer;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.ConvertUtil;
import com.miniits.base.utils.FileUtil;
import com.miniits.base.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

import static com.miniits.base.utils.SystemDict.AppStatus.APP_STATUS_PENDING;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/6/23
 * @Time: 15:01
 * <p>
 * Description:
 * 官方对外提供的api
 */
@RestController(value = "open-app-store")
@RequestMapping("/miniits/apps")
public class AppStoreController extends BaseController {

    @Autowired
    private AppStoreServer appStoreServer;

    @GetMapping("/apps")
    public Result apps(Pageable pageable) {
        if (!StringUtils.isEmpty(pageable.getFilters())) {
//            pageable.setFilters(pageable.getFilters());
        }
        pageable.setPageSize(16);
        Page<AppStore> appStores = appStoreServer.search(pageable);
        List<AppStoresVO> appStoresVOS = (List<AppStoresVO>) ConvertUtil.toVOS(appStores.getContent(), AppStoresVO.class);
        return page(appStoresVOS).page(pageable.getPageSize()).size(pageable.getPageNumber()).
                totalCount(appStores.getTotalElements()).total(appStores.getTotalElements());
    }

    @GetMapping("/app")
    public Result app(
            @RequestParam(value = "app-am") String ascriptionNumber,
            @RequestParam(value = "app-sm") String systemNumber) {
        if (StringUtils.isEmpty(ascriptionNumber) || StringUtils.isEmpty(systemNumber)) {
            return error("非法请求");
        }
        AppStore appStore = appStoreServer.findBySystemNumberAndAscriptionNumber(ascriptionNumber, systemNumber);
        if (ObjectUtils.isEmpty(appStore)) {
            return error("查无此应用");
        }
        AppVO appVO = ConvertUtil.toVO(appStore.getAppContent(), AppVO.class);
        return success(appVO);
    }

    @PostMapping("/push")
    public Result push(@RequestBody String appStore) throws Exception {
        AppStore store = toEntity(appStore, AppStore.class);
        String[] an = store.getAscriptionNumber().split("_N");
        if (an.length < 2) {
            return error("非法推送，您的归属编号错误");
        }
        if (StringUtils.isEmpty(store.getAppImageUrl())) {
            return error("请添加应用的预览图片");
        }
        store.setSystemNumber(an[0] + "_S" + System.currentTimeMillis());
        store.setAppStatus(APP_STATUS_PENDING);
        store.setAppName(store.getAppContent().getComponentName());
        store.setAppStatusName("待审核");
        store.setUpTime(new Date());
        store.setDownloadNumber(0);
        appStoreServer.save(store);
        return success("应用推送成功");
    }

    private static String path = "D:/";
    private static String filenameTemp;

    @GetMapping("/download")
    public void download(@RequestParam(value = "app_id") String appId, HttpServletResponse response) throws Exception {

        AppStore appStore = appStoreServer.download(appId);
        String fileName = "M_PLUS_" + System.currentTimeMillis() + "_" + appStore.getAppName();
        AppContent appContent = ConvertUtil.toVO(appStore.getAppContent(), AppContent.class);
        appContent.setId("");
        appContent.setCreateDate(null);
        appContent.setCreateBy("");
        appContent.setModifyDate(null);
        appContent.setModifyBy("");
        FileUtil.exportFile(response, fileName + ".txt", toJson(appContent));

    }

    public static void main(String[] args) throws IOException {

    }


    /**
     * 创建文件
     *
     * @throws IOException
     */
    public static void creatTxtFile(String name) throws IOException {
        filenameTemp = path + name + ".txt";
        File filename = new File(filenameTemp);
        if (!filename.exists()) {
            filename.createNewFile();
        }
    }

    public static boolean writeTxtFile(String newStr) throws IOException {
        // 先读取原有文件内容，然后进行写入操作
        boolean flag = false;
        String filein = newStr + "\r\n";
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            // 文件路径
            File file = new File(filenameTemp);
            // 将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();

            // 保存该文件原有的内容
            for (int j = 1; (temp = br.readLine()) != null; j++) {
                buf = buf.append(temp);
                // System.getProperty("line.separator")
                // 行与行之间的分隔符 相当于“\n”
                buf = buf.append(System.getProperty("line.separator"));
            }
            buf.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            flag = true;
        } catch (IOException e1) {
            // TODO 自动生成 catch 块
            throw e1;
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return flag;
    }


}
