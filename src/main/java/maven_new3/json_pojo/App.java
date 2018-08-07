package maven_new3.json_pojo;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.abc.pojo.Address;
import com.abc.pojo.Customer;
import com.abc.pojo.PaymentMethod;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args)
			throws JsonGenerationException, JsonMappingException, IOException, DatatypeConfigurationException

	{
//		serialize();
		deserialize();
		System.out.println("Hello World!");
	}

	private static void serialize()
			throws JsonGenerationException, JsonMappingException, IOException, DatatypeConfigurationException {
		ObjectMapper objectMapper = new ObjectMapper();
		// convert Object to json string
		Customer cus = createCustomer();

		// configure Object mapper for pretty print
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

		// writing to console, can write to any output stream such as file
		objectMapper.writeValue(System.out, cus);
		objectMapper.writeValue(new PrintWriter("Customer.json"), cus);
	}

	private static void deserialize() throws IOException {

		// read json file data to String
		byte[] jsonData = Files.readAllBytes(Paths.get("Customer.json"));

		// create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();

		// convert json string to object
		Customer cus = objectMapper.readValue(jsonData, Customer.class);

		System.out.println("Employee Object\n" + cus);
	}

	private static Customer createCustomer() throws DatatypeConfigurationException {

		Customer c = new Customer();
		c.setName("Akhil");
		c.setCustomerId(1);
		c.setAnnualSalary(78324);
		GregorianCalendar c1 = new GregorianCalendar();
		c1.set(1995, 05, 29);
		XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c1);
		c.setDateOfBirth(date2);

		Address address1 = new Address();
		address1.setStreetName("2527 Louise Street");
		address1.setCity("Denton");
		address1.setState("TX");
		address1.setPinCode("76201");

		Address address2 = new Address();
		address2.setStreetName("2126 Stella Street");
		address2.setCity("Denton");
		address2.setState("TX");
		address2.setPinCode("76201");

		c.getAddress().add(address1);
		c.getAddress().add(address2);

		PaymentMethod pm1 = new PaymentMethod();
		pm1.setCard("CREDIT CARD");
		pm1.setCardName("Master");
		pm1.setCardNumber(48723635);
		GregorianCalendar c2 = new GregorianCalendar();
		c2.set(2016, 05, 29);
		XMLGregorianCalendar date3 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c2);
		pm1.setDateFrom(date3);

		GregorianCalendar c3 = new GregorianCalendar();
		c3.set(2012, 05, 29);
		XMLGregorianCalendar date4 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c3);
		pm1.setDateTo(date4);

		c.getPayment().add(pm1);

		return c;

	}
}
