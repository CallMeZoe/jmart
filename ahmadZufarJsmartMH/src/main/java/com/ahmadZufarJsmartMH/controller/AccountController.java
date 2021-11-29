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

	public JsonTable<Account> getJsonTable() {
		return accountTable;
	}

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

	@PostMapping("/{id}/registerStore")
	Store registerStore
			(
					@PathVariable int id,
					@RequestParam String name,
					@RequestParam String address,
					@RequestParam String phoneNumber
			)
	{
		Account acc = Algorithm.<Account> find(accountTable, obj -> obj.id == id);
		if(acc == null || acc.store != null)
		{
			return null;
		}
		acc.store = new Store(name,address, phoneNumber, 0.0);
		return acc.store;
//		return null;
	}

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


	@GetMapping
	String index() { return "account page"; }
}