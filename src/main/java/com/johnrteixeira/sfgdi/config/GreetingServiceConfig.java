package com.johnrteixeira.sfgdi.config;

import com.johnrteixeira.pets.DogPetService;
import com.johnrteixeira.pets.PetService;
import com.johnrteixeira.pets.PetServiceFactory;
import com.johnrteixeira.sfgdi.repositories.EnglishGreetingRepository;
import com.johnrteixeira.sfgdi.repositories.EnglishGreetingRepositoryImpl;
import com.johnrteixeira.sfgdi.repositories.SpanishGreetingRepository;
import com.johnrteixeira.sfgdi.repositories.SpanishGreetingRepositoryImpl;
import com.johnrteixeira.sfgdi.services.*;
import org.springframework.context.annotation.*;

@ImportResource("classpath:sfgdi-config.xml")
@Configuration
public class GreetingServiceConfig {

    @Bean
    PetServiceFactory petServiceFactory(){
        return new PetServiceFactory();
    }

    @Profile({"dog", "default"})
    @Bean
    PetService dogPetService(PetServiceFactory petServiceFactory){
        return petServiceFactory.getPetService("dog");
    }

    @Bean
    @Profile({"cat"})
    PetService catPetService(PetServiceFactory petServiceFactory){
        return petServiceFactory.getPetService("cat");
    }

    @Bean
    SpanishGreetingRepository spanishGreetingRepository(){
        return new SpanishGreetingRepositoryImpl();
    }

    @Profile({"ES", "default"})
    @Bean("i18nService") //spring uses the method name unless overrode
    I18nSpanishGreetingService i18nSpanishGreetingService(SpanishGreetingRepository spanishGreetingRepository){
        return new I18nSpanishGreetingService(spanishGreetingRepository);
    }

    @Bean
    EnglishGreetingRepository englishGreetingRepository(){
        return new EnglishGreetingRepositoryImpl();
    }

    @Profile("EN")
    @Bean
    I18nEnglishGreetingService i18nService(EnglishGreetingRepository englishGreetingRepository){
        return new I18nEnglishGreetingService(englishGreetingRepository);
    }

    @Primary
    @Bean
    PrimaryGreetingService primaryGreetingService(){
        return new PrimaryGreetingService();
    }

    //@Bean
    ConstructorGreetingService constructorGreetingService(){
        return new ConstructorGreetingService();
    }

    @Bean
    PropertyInjectedGreetingService propertyInjectedGreetingService(){
        return new PropertyInjectedGreetingService();
    }

    @Bean
    SetterInjectedGreetingService setterInjectedGreetingService(){
        return new SetterInjectedGreetingService();
    }
}
