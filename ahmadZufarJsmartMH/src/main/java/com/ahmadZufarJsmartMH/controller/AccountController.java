package com.ahmadZufarJsmartMH.controller;

import com.ahmadZufarJsmartMH.Account;
import com.ahmadZufarJsmartMH.Algorithm;
import com.ahmadZufarJsmartMH.Store;
import com.ahmadZufarJsmartMH.dbjson.JsonAutowired;
import com.ahmadZufarJsmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class untuk mengatur perubahan pada objek account
 */

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
	public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+(\\.[a-zA-Z0-9&*_~]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
	public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.* ).{8,}$";
	public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
	public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);

	@JsonAutowired(value = Account.class, filepath = "C:\\Users\\Zufar\\Desktop\\Prak_OOP\\jmart\\ahmadZufarJsmartMH\\src\\main\\account.json")
	public static JsonTable<Account> accountTable;

	/**
	 * Method untuk memberi list berisi objek Account yang ada pada json
	 * @return  list yang berisikan objek Account yang terdaftar pada file json
	 */
	public JsonTable<Account> getJsonTable() {
		return accountTable;
	}

	/**
	 * Method untuk memastikan data kredensial saat melakukan login
	 * @param email email dari objek account
	 * @param password password dari objek account
	 * @return objek account yang memiliki data yang cocok, bila tidak cocok keluarkan null
	 */
	@PostMapping("/login")
	Account login(
			@RequestParam String email,
			@RequestParam String password
	)
	{
		for(Account account : accountTable) {
			if(account.email.equals(email) && account.password.equals(hashPassword(password)))
				return account;
		}
		return null;
	}

	/**
	 * Method untuk pembuatan objek account saat register serta memastikan data yang dimasukkan sesuai
	 * @param name nama dari objek account
	 * @param email email dari objek account
	 * @param password password dari objek account
	 * @return register account apabila datanya sesuai, bila tidak maka keluarkan null
	 */

	@PostMapping("/register")
	Account register
			(
					@RequestParam String name,
					@RequestParam String email,
					@RequestParam String password
			)
	{
		Matcher emailMatcher = REGEX_PATTERN_EMAIL.matcher(email);
		boolean emailMatch = emailMatcher.find();
		Matcher passwordMatcher = REGEX_PATTERN_PASSWORD.matcher(password);
		boolean passwordMatch = passwordMatcher.find();
		boolean unique = true;

		for(Account acc: accountTable){
			if(acc.email.equals(email)){
				unique = false;
				break;
			}
		}

		if(!name.isBlank() && emailMatch && passwordMatch && unique){

			Account regAccount = new Account(name, email, hashPassword(password), 0);
			accountTable.add(regAccount);
			return regAccount;

		} else {
			return null;
		}
	}

	/**
	 * Method untuk melakukan pendaftaran store pada objek account
	 * @param id id dari objek account yang melakukan pendaftaran store
	 * @param name nama dari store yang didaftarkan
	 * @param address alamat dari store yang didaftarkan
	 * @param phoneNumber nomor telefon dari  store yang didaftarkan
	 * @return objek Store yang berhasil didaftarkan, dan jika tidak keluarkan null
	 */

	@PostMapping("/{id}/registerStore")
	Store registerStore(@PathVariable int id, @RequestParam String name, @RequestParam String address, @RequestParam String phoneNumber)
	{
		Account a = Algorithm.<Account> find(accountTable, obj -> obj.id == id);
		if(a == null || a.store != null)
		{
			return null;
		}
		a.store = new Store(name, address, phoneNumber, 0.0);
		return a.store;
	}

	/**
	 * Method untuk melakukan Top up pada balance untuk suatu account yang digunakan pada aplikasi
	 * @param id id dari objek Account yang ingin melakukan top up
	 * @param balance balance dari objek Account yang ingin melakukan top up
	 * @return true jika balance pada objek Account berhasil di top up, jika tidak keluarkan false
	 */
	@PostMapping("{id}/topUp")
	boolean topUp(
			@PathVariable int id,
			@RequestParam double balance
	){
		Account acc = getById(id);
		if(acc != null){
			acc.balance = acc.balance + balance;
			return true;
		} else {
			return false;
		}
	}

	public String hashPassword(String password){
		try{
			String generatedPassword = null;

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] bytes = md.digest();

			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < bytes.length; i++){
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
			return generatedPassword;
		} catch (NoSuchAlgorithmException e){
			e.printStackTrace();
			return password;
		}
	}

	/**
	 * Method untuk mengambil objek Account dari file json sesuai id nya
	 * @param id id dari objek Account
	 * @return objek Account yang memiliki id yang sesuai dengan id yang diberikan sebagai parameter
	 */
	@GetMapping("/{id}")
	public Account getByAccountId(@PathVariable int id) { return getById(id); }

	@GetMapping
	String index() { return "account page"; }
}