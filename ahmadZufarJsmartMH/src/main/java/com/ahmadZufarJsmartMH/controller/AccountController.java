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

	@JsonAutowired(value = Account.class, filepath = "D:\\Prak OOP\\jmart\\ahmadZufarJsmartMH\\src\\main\\account.json")
	public static JsonTable<Account> accountTable;

	public JsonTable<Account> getJsonTable() {
		return accountTable;
	}

	@PostMapping("/login")
	Account login(@RequestParam String email, @RequestParam String password)
	{
		for(Account account : accountTable)
		{
			String passwordHash = null;
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
				byte[] bytes = md.digest();
				StringBuilder sb = new StringBuilder();

				for(int i=0; i< bytes.length ;i++)
				{
					sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
				}
				passwordHash = sb.toString();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			boolean emailIsMatched = account.email.equals(email);
			boolean passwordIsMatched = account.password.equals(passwordHash);
			if(emailIsMatched && passwordIsMatched)
			{
				return account;
			}
		}
		return null;
	}

	@PostMapping("/register")
	Account register(@RequestParam String name, @RequestParam String email, @RequestParam String password)
	{
		Matcher matcherEmail = REGEX_PATTERN_EMAIL.matcher(email);
		Matcher matcherPassword = REGEX_PATTERN_PASSWORD.matcher(password);
		boolean isUnique = true;
		for(Account a : accountTable)
		{
			if(a.email.equals(email))
			{
				isUnique = false;
				break;
			}
		}
		if(matcherEmail.find() && matcherPassword.find() && (!name.isBlank()) && isUnique)
		{
			String passwordHash = null;
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
				byte[] bytes = md.digest();
				StringBuilder sb = new StringBuilder();

				for(int i=0; i< bytes.length ;i++)
				{
					sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
				}
				passwordHash = sb.toString();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			Account account = new Account(name, email, passwordHash, 0.0);
			accountTable.add(account);
			return account;
		}
		return null;
	}

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

	@PostMapping("/{id}/topUp")
	boolean topUp(@PathVariable int id, @RequestParam double balance)
	{
		Account a = getById(id);
		if(a != null)
		{
			a.balance += balance;
			return true;
		}
		return false;
	}

//	public static String MD5(String passwordToHash) throws NoSuchAlgorithmException {
//		String generatedPassword = null;
//
//		MessageDigest md = MessageDigest.getInstance("MD5");
//		md.update(passwordToHash.getBytes());
//		byte[] bytes = md.digest();
//		StringBuilder sb = new StringBuilder();
//
//		for(int i=0; i< bytes.length ;i++)
//		{
//			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
//		}
//		generatedPassword = sb.toString();
//		return generatedPassword;
//	}


//	@GetMapping
//	String index() { return "account page"; }
//
//	@PostMapping("/register")
//	Account register
//	(
//		@RequestParam String name,
//		@RequestParam String email,
//		@RequestParam String password
//	)
//	{
//		return new Account(name, email, password, 0);
//	}
//
//	@GetMapping("/{id}")
//	String getById(@PathVariable int id) { return "account id " + id + " not found!"; }
}