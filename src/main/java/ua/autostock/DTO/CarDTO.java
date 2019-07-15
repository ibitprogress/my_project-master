package ua.autostock.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CarDTO extends BaseDTO {

    private List<MultipartFile> photos;

	@NotNull
	@Size(min = 2, max = 35)
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String category;

	@NotNull
	@Size(min = 2, max = 35)
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String make;

	@NotNull
	@Size(min = 1, max = 45)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String model;

	@Min(value = 0)
	@Max(value = 999999)
	private int mileage;

	@NotNull
	@Size(min = 10, max = 10)
	@Pattern(regexp = "^[0-9-]+$", message = "wrong format! 2012-12-12")
	private LocalDate dateOfManufacture;

	@NotNull
	@Size(min = 10, max = 10)
	@Pattern(regexp = "^[0-9-]+$")
	private LocalDate addTime;

	//переписати regex!!!
	@NotNull
	@Size(min = 17, max = 17)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String vin;

	@NotNull
	@Min(value = 0)
	@Max(value = 9999999)
	private int price;

	@NotNull
	@Size(min = 2, max = 50)
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String color;

	@NotNull
	@Min(value = 1, message = "min - 1")
	@Max(value = 99999, message = "max - 99 999")
	private int volume;

	@NotNull
	@Size(min = 1, max = 50)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String drive;

	@NotNull
	@Size(min=1, max = 50)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String transmission;

	@NotNull
	@Size(min = 2, max = 50)
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String body;

	@NotNull
	@Size(min = 2, max = 20)
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String fuel;



	//не працює на фронті валідація
	@NotNull
	@Min(value = 2)
	@Max(value = 5)
	private int numberOfDoors;

	@NotNull
	@Min(value = 2)
	@Max(value = 50)
	private int numberOfSeats;

	private String comfort;

	private String additionalEquipment;

	@NotNull
	private String description;

}
