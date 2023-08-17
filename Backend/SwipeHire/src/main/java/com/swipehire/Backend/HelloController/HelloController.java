package com.swipehire.Backend.HelloController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    @Operation(summary = "Get greeting message", description = "Returns a greeting message from the Spring Boot application.")
    @ApiResponse(responseCode = "200", description = "Greeting message retrieved successfully.")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
