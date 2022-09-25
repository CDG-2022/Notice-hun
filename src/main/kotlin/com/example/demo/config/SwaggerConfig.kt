package com.example.demo.config

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Pageable
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.schema.AlternateTypeRules
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket


@Configuration
class SwaggerConfig {
    @Bean
    fun api(): Docket? {
        return Docket(DocumentationType.OAS_30)
            .alternateTypeRules(
                AlternateTypeRules
                    .newRule(Pageable::class.java, Page::class.java)
            )
            .useDefaultResponseMessages(false)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo()).securityContexts(listOf(securityContext()))
            .securitySchemes(listOf(apiKey()) as List<SecurityScheme>?)
    }

    private fun apiKey(): ApiKey? =
        ApiKey("X-AUTH-TOKEN", "X-AUTH-TOKEN", "header")


    private fun securityContext(): SecurityContext? =
        SecurityContext
            .builder()
            .securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build()


    fun defaultAuth(): List<SecurityReference>? {
        val authorizationScope = AuthorizationScope("global", "accessEverything")
        val authorizationScopes = arrayOfNulls<AuthorizationScope>(1)
        authorizationScopes[0] = authorizationScope
        return listOf(SecurityReference("X-AUTH-TOKEN", authorizationScopes))
    }

    private fun apiInfo(): ApiInfo? =
        ApiInfoBuilder()
            .title("Swagger")
            .description("swagger config")
            .version("1.0")
            .build()

    @ApiModel
    internal class Page {
        @ApiModelProperty(value = "페이지번호")
        private val page: Int? = null
    }
}