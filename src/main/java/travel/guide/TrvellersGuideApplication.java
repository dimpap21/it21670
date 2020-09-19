package travel.guide;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import travel.guide.GetDataFromApi.GetData;

@SpringBootApplication
public class TrvellersGuideApplication {

	public static void main(String[] args) throws IOException {
		//GetData.GetCityHotels("Athens");
		SpringApplication.run(TrvellersGuideApplication.class, args);
	}

}
