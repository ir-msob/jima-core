package ir.msob.jima.core.api.restful.beans.webclient;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static ir.msob.jima.core.commons.Constants.DEFAULT_CLIENT_REGISTRATION_ID;

/**
 * This configuration class, WebClientConfiguration, is responsible for configuring and customizing the behavior of WebClient instances.
 * It sets up OAuth2 client integration, load balancing, and logging of requests.
 * <p>
 * Author: Yaqub Abdi
 */
@CommonsLog
@Configuration
public class WebClientConfiguration {

    /**
     * Create an ExchangeFilterFunction for logging HTTP requests.
     *
     * @return The ExchangeFilterFunction that logs requests.
     */
    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request: " + clientRequest.method() + " " + clientRequest.url());
            return Mono.just(clientRequest);
        });
    }

    /**
     * Create a primary WebClient instance for making HTTP requests.
     *
     * @param builder The WebClient.Builder used to create the WebClient instance.
     * @return The primary WebClient instance.
     */
    @Bean
    @Primary
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }

    /**
     * Create a primary load-balanced WebClient.Builder with OAuth2 integration.
     *
     * @param authorizedClientManager The manager for OAuth2 authorized clients.
     * @return The load-balanced WebClient.Builder instance.
     */
    @Bean
    @Primary
    @LoadBalanced
    public WebClient.Builder webClientBuilder(AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager authorizedClientManager) {
        // Create a ServerOAuth2AuthorizedClientExchangeFilterFunction to handle OAuth2 integration.
        ServerOAuth2AuthorizedClientExchangeFilterFunction oauth = new ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
        oauth.setDefaultOAuth2AuthorizedClient(true);
        oauth.setDefaultClientRegistrationId(DEFAULT_CLIENT_REGISTRATION_ID);

        return WebClient.builder()
                .filter(oauth)  // Apply OAuth2 client integration.
                .filters(exchangeFilterFunctions -> exchangeFilterFunctions.add(logRequest()));  // Log HTTP requests.
    }
}
