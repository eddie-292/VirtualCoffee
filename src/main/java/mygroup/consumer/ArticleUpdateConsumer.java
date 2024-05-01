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

package mygroup.consumer;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson2.JSON;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import mygroup.common.constant.QueueConstant;
import mygroup.dto.ArticleDoc;
import mygroup.dto.CodeSnippetDoc;
import mygroup.util.SummaryExtractor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author Eddie.BaoZeBing
 * @date 2023/10/12 21:04
 * 文章更新
 */
@Slf4j
@Component
@RabbitListener(queues = QueueConstant.Article_Update)
public class ArticleUpdateConsumer {

    @Autowired
    private MongoTemplate mongoTemplate;
    public static final String COLLECTION_NAME = "Article";

    @RabbitHandler
    public void process(String jsonData) {

        Map map = JSON.parseObject(jsonData, Map.class);
        String articleId = map.get("articleId").toString();
        Long userId = Long.valueOf(map.get("userId").toString());
        String platform = map.get("platform").toString();

        Criteria criteria = Criteria.where("_id").is(articleId)
                .and("creatorUserId").is(userId);
        Query query = new Query(criteria);
        Update update = new Update()
                .set("platform", platform)
                .set("content", map.get("content").toString())
                .set("summary", SummaryExtractor.extractSummary(map.get("content").toString()))
                .set("title", map.get("title").toString())
                .set("category", map.get("category").toString())
                .set("categoryTitle", map.get("categoryTitle").toString())
                .set("updateTime", new Date());
        UpdateResult result = mongoTemplate.upsert(query, update, ArticleDoc.class, COLLECTION_NAME);
        if (result.getMatchedCount() <= 0) {
            log.info("更新文章失败，没有匹配到任何数据");
        } else {
            log.info("用户[{}]更新了文章[{}]", userId, articleId);
        }

    }

}
