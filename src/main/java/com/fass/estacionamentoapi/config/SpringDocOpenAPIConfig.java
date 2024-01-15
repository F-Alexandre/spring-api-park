package com.fass.estacionamentoapi.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenAPIConfig {

   @Bean
    public OpenAPI openAPI(){
       return new OpenAPI()
               .info( new Info()
                       .title("Rest APi - Spring Park")
                       .description("API para gestao de estacionamentode veiculos")
                       .version("v1")
               );
   }

}
