/*
 * Copyright Statement and License Information for Virtual Coffee Kafeih.com Community
 *
 * Copyright Owner：Kafeih.com Community and its contributors, since the inception of the project.
 *
 * License Type：All code, documentation, and design works related to the Kafeih.com Community are licensed under the GNU Affero General Public License (AGPL) v3 or any later version.
 *
 * Use and Distribution：You are free to use, copy, modify, and distribute the code, documentation, and design works of the Kafeih.com Community, subject to the following conditions:
 *
 * 1. You must include the original copyright and license notices in all copies distributed or made publicly available.
 * 2. If you modify the code or design, or derive new works from those provided by the community, you must release these modifications or derivative works under the terms of the AGPLv3 license.
 * 3. Important Note: If you use the code or design of this community to provide network services, you must ensure that all users accessing the service through the network can access the corresponding source code.
 *
 * No Warranty：The Kafeih.com Community and its code, documentation, and design works are provided "as is" without any warranty, including but not limited to warranties of merchantability, fitness for a particular purpose, and non-infringement.
 *
 * License Acquisition：The complete text of the GNU Affero General Public License (AGPL) v3 can be found on the GNU official website.
 *
 * Please note that the above statement only applies to the Kafeih.com Community and the code, documentation, and design works provided by it. Third-party links or resources may be subject to different licenses from their respective owners or publishers. When using these resources, please be sure to comply with the terms of their respective licenses.
 */

package mygroup.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import mygroup.dto.common.ConditionQuery;
import mygroup.dto.common.PageQuery;
import mygroup.entity.UserJoinActivityRelation;
import mygroup.mapper.UserJoinActivityRelationMapper;
import mygroup.service.IUserJoinActivityRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:用户与活动关联 服务接口实现类
 * @author Eddie
 * @date 2024-02-07
 */
@Service
public class UserJoinActivityRelationServiceImpl extends ServiceImpl<UserJoinActivityRelationMapper, UserJoinActivityRelation> implements IUserJoinActivityRelationService {
	@Autowired
	UserJoinActivityRelationMapper userJoinActivityRelationMapper;

	@Override
	public List<UserJoinActivityRelation> queryByCondition(ConditionQuery conditionQuery) {
		return null;
	}

	@Override
	public IPage<UserJoinActivityRelation> queryByPage(PageQuery pageQuery) {
		return null;
	}
}
