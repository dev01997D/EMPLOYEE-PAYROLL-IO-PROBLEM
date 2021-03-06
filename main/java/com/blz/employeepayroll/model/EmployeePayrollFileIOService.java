package com.blz.employeepayroll.model;

import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.*;

public class EmployeePayrollFileIOService {
	public static String PAYROLL_FILE_NAME = "E:\\Eclipse-Workspace-java-developer\\EmployeePayrollService\\src\\main\\java\\com\\blz\\employeepayroll\\model\\payroll-file.txt";

	public void writeData(List<EmployeePayrollData> employeePayrollList) {
		StringBuffer employeeBuffer = new StringBuffer();
		employeePayrollList.forEach(employee -> {
			String employeeDataString = employee.toString().concat("\n");
			employeeBuffer.append(employeeDataString);
		});
		try {
			Files.write(Paths.get(PAYROLL_FILE_NAME), employeeBuffer.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long countEntries(List<EmployeePayrollData> employeePayrollList) {
		long entries = 0;
		try {
			entries = Files.lines(new File(PAYROLL_FILE_NAME).toPath()).count();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entries;
	}

	public void printData(List<EmployeePayrollData> employeePayrollList) {
		try {
			Files.lines(new File(PAYROLL_FILE_NAME).toPath()).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
