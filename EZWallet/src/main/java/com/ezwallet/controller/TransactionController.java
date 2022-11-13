package com.ezwallet.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ezwallet.exception.CustomerException;
import com.ezwallet.exception.TransactionException;
import com.ezwallet.exception.WalletException;
import com.ezwallet.model.Transaction;
import com.ezwallet.model.TransactionDTO;
import com.ezwallet.service.TransactionService;
import com.ezwallet.model.Wallet;

@RestController
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	
	
	
//	@PostMapping("/transaction")
//	public ResponseEntity<Transaction> saveTransaction(@RequestBody Transaction tran) throws TransactionException, WalletException{
//		Transaction tr= transactionService.addTransaction(tran);
//		return new	ResponseEntity<Transaction>(tr,HttpStatus.ACCEPTED);	
//	}
	
	
	
	
	@GetMapping("transactionbytwodate")
	public ResponseEntity<List<TransactionDTO>> viewByTwoDate(@RequestParam("one") String one, @RequestParam("two")  String two) throws TransactionException{
		LocalDate firstDate= LocalDate.parse(one);
		LocalDate secondDate = LocalDate.parse(two);
		List<Transaction> listOTransactions = transactionService.viewTransactionByDate(firstDate, secondDate);
		
		List<TransactionDTO> listOfTransactionDTOs= new ArrayList<>();
		for(Transaction transaction:listOTransactions) {
			
			TransactionDTO dto = new TransactionDTO();
			dto.setAmount(transaction.getAmount());
			dto.setDescription(transaction.getDescription());
			dto.setTransactionDate(transaction.getTransactionDate());
			dto.setTransactionId(transaction.getTransactionId());
			dto.setTransactionType(transaction.getTransactionType());	
			listOfTransactionDTOs.add(dto);	
		}
		return new ResponseEntity<List<TransactionDTO>>(listOfTransactionDTOs,HttpStatus.ACCEPTED);
	
	}
	
	
	
	
	@GetMapping("/transactiontype/{type}")
	public ResponseEntity<List<TransactionDTO>> viewAllTransacationByType(@PathVariable("type") String type) throws TransactionException{
		List<Transaction> listTransactions=transactionService.findByTransactionType(type);
		
		List<TransactionDTO> listOfTransactionDTOs= new ArrayList<>();
		for(Transaction transaction:listTransactions) {
			
			TransactionDTO dto = new TransactionDTO();
			dto.setAmount(transaction.getAmount());
			dto.setDescription(transaction.getDescription());
			dto.setTransactionDate(transaction.getTransactionDate());
			dto.setTransactionId(transaction.getTransactionId());
			dto.setTransactionType(transaction.getTransactionType());	
			listOfTransactionDTOs.add(dto);	
		}
		return new ResponseEntity<List<TransactionDTO>>(listOfTransactionDTOs,HttpStatus.ACCEPTED);
	
	}
	
	
	
	@GetMapping("/transaction/{id}")
	public ResponseEntity<TransactionDTO> findById(@PathVariable("id") Integer id) throws TransactionException{
	
		Transaction transaction = transactionService.findByTransactionId(id);	
		TransactionDTO dto = new TransactionDTO();
		dto.setAmount(transaction.getAmount());
		dto.setDescription(transaction.getDescription());
		dto.setTransactionDate(transaction.getTransactionDate());
		dto.setTransactionId(transaction.getTransactionId());
		dto.setTransactionType(transaction.getTransactionType());
		return new ResponseEntity<TransactionDTO>(dto, HttpStatus.CREATED);
	}
	
	
	@PostMapping("/gettransactionlist")
	public ResponseEntity<List<TransactionDTO>> viewByWallet(@RequestParam String key) throws TransactionException, WalletException, CustomerException{
		List<Transaction> listTransactions= transactionService.findByWallet(key);
		
		List<TransactionDTO> listOfTransactionDTOs= new ArrayList<>();
		for(Transaction transaction:listTransactions) {
			
			TransactionDTO dto = new TransactionDTO();
			dto.setAmount(transaction.getAmount());
			dto.setDescription(transaction.getDescription());
			dto.setTransactionDate(transaction.getTransactionDate());
			dto.setTransactionId(transaction.getTransactionId());
			dto.setTransactionType(transaction.getTransactionType());	
			listOfTransactionDTOs.add(dto);	
		}
		return new ResponseEntity<List<TransactionDTO>>(listOfTransactionDTOs,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/transaction")
	public ResponseEntity<List<TransactionDTO>>  viewAllTransaction() throws TransactionException{
		List<Transaction>  listOfTransactions = transactionService.viewAllTransaction();
		List<TransactionDTO> listOfTransactionDTOs= new ArrayList<>();
		for(Transaction transaction:listOfTransactions) {	
			TransactionDTO dto = new TransactionDTO();
			dto.setAmount(transaction.getAmount());
			dto.setDescription(transaction.getDescription());
			dto.setTransactionDate(transaction.getTransactionDate());
			dto.setTransactionId(transaction.getTransactionId());
			dto.setTransactionType(transaction.getTransactionType());
			
			listOfTransactionDTOs.add(dto);	
		}
		return new ResponseEntity<List<TransactionDTO>>(listOfTransactionDTOs,HttpStatus.OK);
	}
	
	
	

	@GetMapping("/transactionbydate/{date}")
	public ResponseEntity<List<TransactionDTO>> viewByDate(@RequestParam("date") String date) throws TransactionException{
		LocalDate sdate = LocalDate.parse(date);
		List<Transaction> listOfTransactions=  transactionService.findByDate(sdate);
		
		List<TransactionDTO> listOfTransactionDTOs= new ArrayList<>();	
		for(Transaction transaction:listOfTransactions) {
			TransactionDTO dto = new TransactionDTO();
			dto.setAmount(transaction.getAmount());
			dto.setDescription(transaction.getDescription());
			dto.setTransactionDate(transaction.getTransactionDate());
			dto.setTransactionId(transaction.getTransactionId());
			dto.setTransactionType(transaction.getTransactionType());
			
			listOfTransactionDTOs.add(dto);	
		}
		return new ResponseEntity<List<TransactionDTO>>(listOfTransactionDTOs,HttpStatus.ACCEPTED);
	}
	

}
