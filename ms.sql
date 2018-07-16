/*
Navicat MySQL Data Transfer

Source Server         : ue
Source Server Version : 50720
Source Host           : 192.168.1.113:3306
Source Database       : ms

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-07-16 15:44:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `app_content`
-- ----------------------------
DROP TABLE IF EXISTS `app_content`;
CREATE TABLE `app_content` (
  `id` varchar(32) NOT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(50) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `api_data_structure_type` int(11) DEFAULT NULL,
  `component_body` text NOT NULL,
  `component_body_api` varchar(500) DEFAULT NULL,
  `component_id` varchar(45) NOT NULL,
  `component_name` varchar(30) NOT NULL,
  `component_status` int(11) NOT NULL,
  `component_status_name` varchar(25) DEFAULT NULL,
  `component_type` int(11) DEFAULT NULL,
  `component_type_name` varchar(25) DEFAULT NULL,
  `data_filters` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_content
-- ----------------------------

-- ----------------------------
-- Table structure for `app_store`
-- ----------------------------
DROP TABLE IF EXISTS `app_store`;
CREATE TABLE `app_store` (
  `id` varchar(32) NOT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(50) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `app_status` int(11) NOT NULL,
  `app_status_name` varchar(30) DEFAULT NULL,
  `app_type` int(11) NOT NULL,
  `app_type_name` varchar(30) DEFAULT NULL,
  `author_id` varchar(36) NOT NULL,
  `author_name` varchar(50) DEFAULT NULL,
  `collection_number` int(11) DEFAULT NULL,
  `down_time` datetime DEFAULT NULL,
  `download_number` int(11) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `star_number` int(11) DEFAULT NULL,
  `up_time` datetime NOT NULL,
  `app_content_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4sgnqe5d6g3wxel874n1uih4c` (`app_content_id`),
  CONSTRAINT `FK4sgnqe5d6g3wxel874n1uih4c` FOREIGN KEY (`app_content_id`) REFERENCES `app_content` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_store
-- ----------------------------

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` varchar(32) NOT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(50) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `allow_comment` int(11) DEFAULT NULL,
  `author_id` varchar(255) DEFAULT NULL,
  `author_name` varchar(255) DEFAULT NULL,
  `comments_num` int(11) DEFAULT NULL,
  `content` text NOT NULL,
  `content_title` varchar(255) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `path` bigint(20) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `source` int(11) DEFAULT NULL,
  `source_name` varchar(255) DEFAULT NULL,
  `sourceurl` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `status_name` varchar(255) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `title_image` varchar(255) DEFAULT NULL,
  `title_name` varchar(255) DEFAULT NULL,
  `type_names` varchar(255) DEFAULT NULL,
  `types` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------

-- ----------------------------
-- Table structure for `article_catrgory_associate`
-- ----------------------------
DROP TABLE IF EXISTS `article_catrgory_associate`;
CREATE TABLE `article_catrgory_associate` (
  `id` varchar(32) NOT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(50) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `article_id` varchar(32) DEFAULT NULL,
  `categorie_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4h76kb7b8bxjmo24uy8k310x9` (`article_id`),
  KEY `FK9dxhj3jmyrdap7g8osqcqryru` (`categorie_id`),
  CONSTRAINT `FK4h76kb7b8bxjmo24uy8k310x9` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
  CONSTRAINT `FK9dxhj3jmyrdap7g8osqcqryru` FOREIGN KEY (`categorie_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_catrgory_associate
-- ----------------------------

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` varchar(32) NOT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(50) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `category_name` varchar(45) NOT NULL,
  `level` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `p_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lroeo5fvfdeg4hpicn4lw7x9b` (`category_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------

-- ----------------------------
-- Table structure for `component`
-- ----------------------------
DROP TABLE IF EXISTS `component`;
CREATE TABLE `component` (
  `id` varchar(32) NOT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(50) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `api_data_structure_type` int(11) DEFAULT NULL,
  `component_body` text NOT NULL,
  `component_body_api` varchar(255) DEFAULT NULL,
  `component_id` varchar(255) DEFAULT NULL,
  `component_name` varchar(255) DEFAULT NULL,
  `component_status` int(11) DEFAULT NULL,
  `component_status_name` varchar(255) DEFAULT NULL,
  `data_filters` varchar(255) DEFAULT NULL,
  `component_type` int(11) DEFAULT NULL,
  `component_type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of component
-- ----------------------------
INSERT INTO `component` VALUES ('40288100630b752201630b7872290007', null, '2018-04-28 16:57:13', null, null, null, '<div class=\"container\">\n	<div class=\"row clearfix\">\n		<div class=\"col-md-12 column\">\n			<nav class=\"navbar navbar-default\" role=\"navigation\">\n				<div class=\"navbar-header\">\n					 <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\"> <span class=\"sr-only\">Toggle navigation</span><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span></button> <a class=\"navbar-brand\" href=\"#\">Brand</a>\n				</div>\n				\n				<div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n					<ul class=\"nav navbar-nav\">\n						<li class=\"active\">\n							 <a href=\"#\">Link</a>\n						</li>\n						<li>\n							 <a href=\"#\">Link</a>\n						</li>\n						<li class=\"dropdown\">\n							 <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Dropdown<strong class=\"caret\"></strong></a>\n							<ul class=\"dropdown-menu\">\n								<li>\n									 <a href=\"#\">Action</a>\n								</li>\n								<li>\n									 <a href=\"#\">Another action</a>\n								</li>\n								<li>\n									 <a href=\"#\">Something else here</a>\n								</li>\n								<li class=\"divider\">\n								</li>\n								<li>\n									 <a href=\"#\">Separated link</a>\n								</li>\n								<li class=\"divider\">\n								</li>\n								<li>\n									 <a href=\"#\">One more separated link</a>\n								</li>\n							</ul>\n						</li>\n					</ul>\n					<form class=\"navbar-form navbar-left\" role=\"search\">\n						<div class=\"form-group\">\n							<input class=\"form-control\" type=\"text\" />\n						</div> <button type=\"submit\" class=\"btn btn-default\">Submit</button>\n					</form>\n					<ul class=\"nav navbar-nav navbar-right\">\n						<li>\n							 <a href=\"#\">Link</a>\n						</li>\n						<li class=\"dropdown\">\n							 <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Dropdown<strong class=\"caret\"></strong></a>\n							<ul class=\"dropdown-menu\">\n								<li>\n									 <a href=\"#\">Action</a>\n								</li>\n								<li>\n									 <a href=\"#\">Another action</a>\n								</li>\n								<li>\n									 <a href=\"#\">Something else here</a>\n								</li>\n								<li class=\"divider\">\n								</li>\n								<li>\n									 <a href=\"#\">Separated link</a>\n								</li>\n							</ul>\n						</li>\n					</ul>\n				</div>\n				\n			</nav>\n		</div>\n	</div>\n</div>', null, '1524905832999', '132', '100000001', '启用', '', null, null);
INSERT INTO `component` VALUES ('40288100630b752201630b79a137000a', null, '2018-04-28 16:58:31', null, null, null, '<div>fsdfsdfsdsdf</div>', null, '1524905910574', '123', '100000001', '启用', '', null, null);

-- ----------------------------
-- Table structure for `component_image`
-- ----------------------------
DROP TABLE IF EXISTS `component_image`;
CREATE TABLE `component_image` (
  `id` varchar(32) NOT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(50) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `api_data_structure_type` int(11) DEFAULT NULL,
  `component_body` text NOT NULL,
  `component_body_api` varchar(255) DEFAULT NULL,
  `component_id` varchar(255) DEFAULT NULL,
  `component_name` varchar(255) DEFAULT NULL,
  `component_reference_id` varchar(255) DEFAULT NULL,
  `component_status` int(11) DEFAULT NULL,
  `component_status_name` varchar(255) DEFAULT NULL,
  `component_type` int(11) DEFAULT NULL,
  `component_type_name` varchar(255) DEFAULT NULL,
  `data_filters` varchar(255) DEFAULT NULL,
  `component_source` int(11) DEFAULT NULL,
  `component_source_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of component_image
-- ----------------------------
INSERT INTO `component_image` VALUES ('40288100630b752201630b767be20002', null, '2018-04-28 16:55:04', null, null, null, '<div componentId=1524905704386 componentName=index_title style=\'width:100%;height:auto\' ></div>', null, '1524905704386', 'index_title', null, '100000001', '启用', '100006001', '系统', null, null, null);
INSERT INTO `component_image` VALUES ('40288100630b752201630b767be30004', null, '2018-04-28 16:55:04', null, null, null, '<div componentId=1524905704387 componentName=index_body style=\'width:100%;height:auto\' ></div>', null, '1524905704387', 'index_body', null, '100000001', '启用', '100006001', '系统', null, null, null);
INSERT INTO `component_image` VALUES ('40288100630b752201630b767be40006', null, '2018-04-28 16:55:04', null, null, null, '<div componentId=1524905704388 componentName=index_footer style=\'width:100%;height:auto\' ></div>', null, '1524905704388', 'index_footer', null, '100000001', '启用', '100006001', '系统', null, null, null);
INSERT INTO `component_image` VALUES ('40288100630b752201630b78a3b90009', null, '2018-04-28 16:57:26', null, null, null, '<div class=\"container\">\n	<div class=\"row clearfix\">\n		<div class=\"col-md-12 column\">\n			<nav class=\"navbar navbar-default\" role=\"navigation\">\n				<div class=\"navbar-header\">\n					 <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\"> <span class=\"sr-only\">Toggle navigation</span><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span></button> <a class=\"navbar-brand\" href=\"#\">Brand</a>\n				</div>\n				\n				<div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n					<ul class=\"nav navbar-nav\">\n						<li class=\"active\">\n							 <a href=\"#\">Link</a>\n						</li>\n						<li>\n							 <a href=\"#\">Link</a>\n						</li>\n						<li class=\"dropdown\">\n							 <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Dropdown<strong class=\"caret\"></strong></a>\n							<ul class=\"dropdown-menu\">\n								<li>\n									 <a href=\"#\">Action</a>\n								</li>\n								<li>\n									 <a href=\"#\">Another action</a>\n								</li>\n								<li>\n									 <a href=\"#\">Something else here</a>\n								</li>\n								<li class=\"divider\">\n								</li>\n								<li>\n									 <a href=\"#\">Separated link</a>\n								</li>\n								<li class=\"divider\">\n								</li>\n								<li>\n									 <a href=\"#\">One more separated link</a>\n								</li>\n							</ul>\n						</li>\n					</ul>\n					<form class=\"navbar-form navbar-left\" role=\"search\">\n						<div class=\"form-group\">\n							<input class=\"form-control\" type=\"text\" />\n						</div> <button type=\"submit\" class=\"btn btn-default\">Submit</button>\n					</form>\n					<ul class=\"nav navbar-nav navbar-right\">\n						<li>\n							 <a href=\"#\">Link</a>\n						</li>\n						<li class=\"dropdown\">\n							 <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Dropdown<strong class=\"caret\"></strong></a>\n							<ul class=\"dropdown-menu\">\n								<li>\n									 <a href=\"#\">Action</a>\n								</li>\n								<li>\n									 <a href=\"#\">Another action</a>\n								</li>\n								<li>\n									 <a href=\"#\">Something else here</a>\n								</li>\n								<li class=\"divider\">\n								</li>\n								<li>\n									 <a href=\"#\">Separated link</a>\n								</li>\n							</ul>\n						</li>\n					</ul>\n				</div>\n				\n			</nav>\n		</div>\n	</div>\n</div>', null, '1524905832999', '132', null, '100000001', '启用', '100006002', 'DIY', '', null, null);
INSERT INTO `component_image` VALUES ('40288100630b752201630b7a2caa000e', null, '2018-04-28 16:59:06', null, null, null, '<div>fsdfsdfsdsdf</div>', null, '1524905910574', '123', null, '100000001', '启用', '100006002', 'DIY', '', null, null);
INSERT INTO `component_image` VALUES ('4028818263d967660163d96853d90002', null, '2018-06-07 16:41:23', null, null, null, '<div componentId=1528360883153 componentName=test_title style=\'width:100%;height:auto\' ></div>', null, '1528360883153', 'test_title', null, '100000001', '启用', null, null, null, '100006001', '系统');
INSERT INTO `component_image` VALUES ('4028818263d967660163d96853d90004', null, '2018-06-07 16:41:23', null, null, null, '<div componentId=1528360883154 componentName=test_body style=\'width:100%;height:auto\' ></div>', null, '1528360883154', 'test_body', null, '100000001', '启用', null, null, null, '100006001', '系统');
INSERT INTO `component_image` VALUES ('4028818263d967660163d96853da0006', null, '2018-06-07 16:41:23', null, null, null, '<div componentId=1528360883155 componentName=test_footer style=\'width:100%;height:auto\' ></div>', null, '1528360883155', 'test_footer', null, '100000001', '启用', null, null, null, '100006001', '系统');
INSERT INTO `component_image` VALUES ('4028818263d967660163d96875ee0008', null, '2018-06-07 16:41:32', null, null, null, '<div>fsdfsdfsdsdf</div>', null, '1524905910574', '123', '40288100630b752201630b79a137000a', '100000001', '启用', null, null, '', '100006002', 'DIY');
INSERT INTO `component_image` VALUES ('4028818263d967660163d96875ee000a', null, '2018-06-07 16:41:32', null, null, null, '<div class=\"container\">\n	<div class=\"row clearfix\">\n		<div class=\"col-md-12 column\">\n			<nav class=\"navbar navbar-default\" role=\"navigation\">\n				<div class=\"navbar-header\">\n					 <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\"> <span class=\"sr-only\">Toggle navigation</span><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span></button> <a class=\"navbar-brand\" href=\"#\">Brand</a>\n				</div>\n				\n				<div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n					<ul class=\"nav navbar-nav\">\n						<li class=\"active\">\n							 <a href=\"#\">Link</a>\n						</li>\n						<li>\n							 <a href=\"#\">Link</a>\n						</li>\n						<li class=\"dropdown\">\n							 <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Dropdown<strong class=\"caret\"></strong></a>\n							<ul class=\"dropdown-menu\">\n								<li>\n									 <a href=\"#\">Action</a>\n								</li>\n								<li>\n									 <a href=\"#\">Another action</a>\n								</li>\n								<li>\n									 <a href=\"#\">Something else here</a>\n								</li>\n								<li class=\"divider\">\n								</li>\n								<li>\n									 <a href=\"#\">Separated link</a>\n								</li>\n								<li class=\"divider\">\n								</li>\n								<li>\n									 <a href=\"#\">One more separated link</a>\n								</li>\n							</ul>\n						</li>\n					</ul>\n					<form class=\"navbar-form navbar-left\" role=\"search\">\n						<div class=\"form-group\">\n							<input class=\"form-control\" type=\"text\" />\n						</div> <button type=\"submit\" class=\"btn btn-default\">Submit</button>\n					</form>\n					<ul class=\"nav navbar-nav navbar-right\">\n						<li>\n							 <a href=\"#\">Link</a>\n						</li>\n						<li class=\"dropdown\">\n							 <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Dropdown<strong class=\"caret\"></strong></a>\n							<ul class=\"dropdown-menu\">\n								<li>\n									 <a href=\"#\">Action</a>\n								</li>\n								<li>\n									 <a href=\"#\">Another action</a>\n								</li>\n								<li>\n									 <a href=\"#\">Something else here</a>\n								</li>\n								<li class=\"divider\">\n								</li>\n								<li>\n									 <a href=\"#\">Separated link</a>\n								</li>\n							</ul>\n						</li>\n					</ul>\n				</div>\n				\n			</nav>\n		</div>\n	</div>\n</div>', null, '1524905832999', '132', '40288100630b752201630b7872290007', '100000001', '启用', null, null, '', '100006002', 'DIY');
INSERT INTO `component_image` VALUES ('ff80818163ba708d0163ba84f6ea0001', null, '2018-06-01 16:44:26', null, null, null, '<div>fsdfsdfsdsdf</div>', null, '1524905910574', '123', '40288100630b752201630b79a137000a', '100000001', '启用', null, null, '', '100006002', 'DIY');
INSERT INTO `component_image` VALUES ('ff80818163ba708d0163ba85113e0003', null, '2018-06-01 16:44:33', null, null, null, '<div>fsdfsdfsdsdf</div>', null, '1524905910574', '123', '40288100630b752201630b79a137000a', '100000001', '启用', null, null, '', '100006002', 'DIY');
INSERT INTO `component_image` VALUES ('ff80818163ba708d0163ba85113e0005', null, '2018-06-01 16:44:33', null, null, null, '<div class=\"container\">\n	<div class=\"row clearfix\">\n		<div class=\"col-md-12 column\">\n			<nav class=\"navbar navbar-default\" role=\"navigation\">\n				<div class=\"navbar-header\">\n					 <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\"> <span class=\"sr-only\">Toggle navigation</span><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span></button> <a class=\"navbar-brand\" href=\"#\">Brand</a>\n				</div>\n				\n				<div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n					<ul class=\"nav navbar-nav\">\n						<li class=\"active\">\n							 <a href=\"#\">Link</a>\n						</li>\n						<li>\n							 <a href=\"#\">Link</a>\n						</li>\n						<li class=\"dropdown\">\n							 <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Dropdown<strong class=\"caret\"></strong></a>\n							<ul class=\"dropdown-menu\">\n								<li>\n									 <a href=\"#\">Action</a>\n								</li>\n								<li>\n									 <a href=\"#\">Another action</a>\n								</li>\n								<li>\n									 <a href=\"#\">Something else here</a>\n								</li>\n								<li class=\"divider\">\n								</li>\n								<li>\n									 <a href=\"#\">Separated link</a>\n								</li>\n								<li class=\"divider\">\n								</li>\n								<li>\n									 <a href=\"#\">One more separated link</a>\n								</li>\n							</ul>\n						</li>\n					</ul>\n					<form class=\"navbar-form navbar-left\" role=\"search\">\n						<div class=\"form-group\">\n							<input class=\"form-control\" type=\"text\" />\n						</div> <button type=\"submit\" class=\"btn btn-default\">Submit</button>\n					</form>\n					<ul class=\"nav navbar-nav navbar-right\">\n						<li>\n							 <a href=\"#\">Link</a>\n						</li>\n						<li class=\"dropdown\">\n							 <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Dropdown<strong class=\"caret\"></strong></a>\n							<ul class=\"dropdown-menu\">\n								<li>\n									 <a href=\"#\">Action</a>\n								</li>\n								<li>\n									 <a href=\"#\">Another action</a>\n								</li>\n								<li>\n									 <a href=\"#\">Something else here</a>\n								</li>\n								<li class=\"divider\">\n								</li>\n								<li>\n									 <a href=\"#\">Separated link</a>\n								</li>\n							</ul>\n						</li>\n					</ul>\n				</div>\n				\n			</nav>\n		</div>\n	</div>\n</div>', null, '1524905832999', '132', '40288100630b752201630b7872290007', '100000001', '启用', null, null, '', '100006002', 'DIY');

-- ----------------------------
-- Table structure for `image`
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `id` varchar(32) NOT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(50) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `bb_code` varchar(500) DEFAULT NULL,
  `html` varchar(500) DEFAULT NULL,
  `markdown` varchar(500) DEFAULT NULL,
  `name` varchar(60) NOT NULL,
  `url` varchar(500) NOT NULL,
  `address_url` varchar(300) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image
-- ----------------------------

-- ----------------------------
-- Table structure for `links`
-- ----------------------------
DROP TABLE IF EXISTS `links`;
CREATE TABLE `links` (
  `id` varchar(32) NOT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(50) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `link_name` varchar(50) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `status_name` varchar(50) NOT NULL,
  `url` varchar(320) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of links
-- ----------------------------

-- ----------------------------
-- Table structure for `logs`
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs` (
  `id` varchar(32) NOT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(50) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `method_name` varchar(255) DEFAULT NULL,
  `module_name` varchar(255) DEFAULT NULL,
  `params` varchar(255) DEFAULT NULL,
  `uri` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logs
-- ----------------------------

-- ----------------------------
-- Table structure for `page`
-- ----------------------------
DROP TABLE IF EXISTS `page`;
CREATE TABLE `page` (
  `id` varchar(32) NOT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(50) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `create_static_file` int(11) NOT NULL,
  `page_alias_name` varchar(255) DEFAULT NULL,
  `page_name` varchar(255) DEFAULT NULL,
  `page_path` varchar(255) DEFAULT NULL,
  `page_status` int(11) DEFAULT NULL,
  `page_status_name` varchar(255) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `key_word` varchar(500) DEFAULT NULL,
  `title` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of page
-- ----------------------------
INSERT INTO `page` VALUES ('40288100630b752201630b767bc80000', null, '2018-04-28 16:55:04', null, null, '100000001', '123', 'index', '', '100000001', '启用', null, null, null);
INSERT INTO `page` VALUES ('4028818263d967660163d96853d40000', null, '2018-06-07 16:41:23', null, null, '100000002', 'test', 'test', 'test', '100000001', '启用', null, null, null);

-- ----------------------------
-- Table structure for `page_component_associate`
-- ----------------------------
DROP TABLE IF EXISTS `page_component_associate`;
CREATE TABLE `page_component_associate` (
  `id` varchar(32) NOT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(50) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `sorts` int(11) DEFAULT NULL,
  `component_image_id` varchar(32) DEFAULT NULL,
  `component_image_p_id` varchar(32) DEFAULT NULL,
  `page_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqov245n03gpexsny12b1x6nyj` (`component_image_id`),
  KEY `FKjolueq9a4vejjv74yq4chlcvr` (`component_image_p_id`),
  KEY `FK8apbufchteqosi93n5slw6ab5` (`page_id`),
  CONSTRAINT `FK8apbufchteqosi93n5slw6ab5` FOREIGN KEY (`page_id`) REFERENCES `page` (`id`),
  CONSTRAINT `FKjolueq9a4vejjv74yq4chlcvr` FOREIGN KEY (`component_image_p_id`) REFERENCES `component_image` (`id`),
  CONSTRAINT `FKqov245n03gpexsny12b1x6nyj` FOREIGN KEY (`component_image_id`) REFERENCES `component_image` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of page_component_associate
-- ----------------------------
INSERT INTO `page_component_associate` VALUES ('40288100630b752201630b767be20001', null, '2018-04-28 16:55:04', null, null, '1', '2', '40288100630b752201630b767be20002', null, '40288100630b752201630b767bc80000');
INSERT INTO `page_component_associate` VALUES ('40288100630b752201630b767be30003', null, '2018-04-28 16:55:04', null, null, '1', '1', '40288100630b752201630b767be30004', null, '40288100630b752201630b767bc80000');
INSERT INTO `page_component_associate` VALUES ('40288100630b752201630b767be30005', null, '2018-04-28 16:55:04', null, null, '1', '3', '40288100630b752201630b767be40006', null, '40288100630b752201630b767bc80000');
INSERT INTO `page_component_associate` VALUES ('40288100630b752201630b78a3b90008', null, '2018-04-28 16:57:26', null, null, '2', '11', '40288100630b752201630b78a3b90009', '40288100630b752201630b767be20002', '40288100630b752201630b767bc80000');
INSERT INTO `page_component_associate` VALUES ('40288100630b752201630b7a2caa000d', null, '2018-04-28 16:59:06', null, null, '2', '21', '40288100630b752201630b7a2caa000e', '40288100630b752201630b767be30004', '40288100630b752201630b767bc80000');
INSERT INTO `page_component_associate` VALUES ('4028818263d967660163d96853d90001', null, '2018-06-07 16:41:23', null, null, '1', '1', '4028818263d967660163d96853d90002', null, '4028818263d967660163d96853d40000');
INSERT INTO `page_component_associate` VALUES ('4028818263d967660163d96853d90003', null, '2018-06-07 16:41:23', null, null, '1', '2', '4028818263d967660163d96853d90004', null, '4028818263d967660163d96853d40000');
INSERT INTO `page_component_associate` VALUES ('4028818263d967660163d96853da0005', null, '2018-06-07 16:41:23', null, null, '1', '3', '4028818263d967660163d96853da0006', null, '4028818263d967660163d96853d40000');
INSERT INTO `page_component_associate` VALUES ('4028818263d967660163d96875ee0007', null, '2018-06-07 16:41:32', null, null, '2', '11', '4028818263d967660163d96875ee0008', '4028818263d967660163d96853d90002', '4028818263d967660163d96853d40000');
INSERT INTO `page_component_associate` VALUES ('4028818263d967660163d96875ee0009', null, '2018-06-07 16:41:32', null, null, '2', '12', '4028818263d967660163d96875ee000a', '4028818263d967660163d96853d90002', '4028818263d967660163d96853d40000');
INSERT INTO `page_component_associate` VALUES ('ff80818163ba708d0163ba84f6e90000', null, '2018-06-01 16:44:26', null, null, '2', '22', 'ff80818163ba708d0163ba84f6ea0001', '40288100630b752201630b767be30004', '40288100630b752201630b767bc80000');
INSERT INTO `page_component_associate` VALUES ('ff80818163ba708d0163ba85113d0002', null, '2018-06-01 16:44:33', null, null, '2', '12', 'ff80818163ba708d0163ba85113e0003', '40288100630b752201630b767be20002', '40288100630b752201630b767bc80000');
INSERT INTO `page_component_associate` VALUES ('ff80818163ba708d0163ba85113e0004', null, '2018-06-01 16:44:33', null, null, '2', '13', 'ff80818163ba708d0163ba85113e0005', '40288100630b752201630b767be20002', '40288100630b752201630b767bc80000');

-- ----------------------------
-- Table structure for `tag`
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` varchar(32) NOT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(50) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1wdpsed5kna2y38hnbgrnhi5b` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(50) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `user_name` varchar(60) DEFAULT NULL,
  `user_status_code` int(11) DEFAULT NULL,
  `user_status_name` varchar(10) DEFAULT NULL,
  `user_type_code` int(11) DEFAULT NULL,
  `user_type_name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
