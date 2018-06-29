package com.miniits.base.controller.open;

import com.miniits.base.model.entity.AppStore;
import com.miniits.base.model.vo.AppStoresVO;
import com.miniits.base.model.vo.AppVO;
import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.AppStoreServer;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.ConvertUtil;
import com.miniits.base.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/6/23
 * @Time: 15:01
 * <p>
 * Description:
 * WWW.MINIITS.COM
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
        return page((List<AppStoresVO>) ConvertUtil.toVOS(appStores.getContent(), AppStoresVO.class)).page(pageable.getPageSize()).size(pageable.getPageNumber()).
                totalCount(appStores.getTotalElements()).total(appStores.getTotalElements());
    }

    @GetMapping("/app")
    public Result app(
            @RequestParam(value = "app-am") String ascriptionNumber,
            @RequestParam(value = "app-sm") String systemNumber) {
        AppStore appStore = appStoreServer.findBySystemNumberAndAscriptionNumber(ascriptionNumber, systemNumber);
        if (ObjectUtils.isEmpty(appStore)) {
            return error("查无此应用");
        }
        AppVO appVO = ConvertUtil.toVO(appStore.getAppContent(), AppVO.class);
        return success(appVO);
    }

}
