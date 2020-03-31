package com.timao.comfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

    // 配置swagger3核心配置 docket
    @Bean
     public Docket createRestApi(){

         return new Docket(DocumentationType.SWAGGER_2)     // 制定api类型位swagger2
                 .apiInfo(apiInfo())                        // 用于定义api文档汇总信息
                 .select().apis(RequestHandlerSelectors
                         .basePackage("com.timao.comtroller"))  //扫描controller
                 .paths(PathSelectors.any())                    //所有controller
                 .build();
     }

     private ApiInfo apiInfo(){
         return new ApiInfoBuilder()
                 .title("天天吃货 平台接口API")                 //文档也标题
                 .contact(new Contact(
                         "timao",
                         "https://www.timao.com",
                         "1456059834@qq.com"))
                 .description("api文档")                      // 备注
                 .version("1.0.1")                           // 版本号
                 .termsOfServiceUrl("https://www.timao.com") // url地址
                 .build();
     }

}
