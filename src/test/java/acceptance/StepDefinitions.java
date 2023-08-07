package acceptance;

import org.springframework.web.client.RestTemplate;

public class StepDefinitions {
    private String server= System.getProperty("calculator.url");
    private RestTemplate restTemplate = new RestTemplate();

    private String a;
    private String b;
    private String result;

    //@Given("I have two numbers:(.*) and (.*) $")
    public void i_have_two_numbers(String a,String b) throws Throwable{
        this.a = a;
        this.b = b;
    }

}
