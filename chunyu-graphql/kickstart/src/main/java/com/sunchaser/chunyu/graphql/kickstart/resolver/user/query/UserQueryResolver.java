package com.sunchaser.chunyu.graphql.kickstart.resolver.user.query;

import com.sunchaser.chunyu.graphql.kickstart.model.SexEnum;
import com.sunchaser.chunyu.graphql.kickstart.model.User;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

/**
 * graphql user query resolver
 *
 * @author sunchaser admin@lilu.org.cn
 * @since JDK8 2022/5/9
 */
@Component
@Slf4j
@Validated
public class UserQueryResolver implements GraphQLQueryResolver {

    public User user(@NotBlank String id) {
        log.info("Retrieving user id: {}", id);
        return User.builder()
                .id(UUID.randomUUID())
                .name("SunChaser")
                .sex(SexEnum.MAN)
                .age(18)
                .build();
    }
}
