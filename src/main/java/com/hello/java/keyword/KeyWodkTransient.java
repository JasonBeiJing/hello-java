package com.hello.java.keyword;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class KeyWodkTransient implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private transient String password;

	public KeyWodkTransient() {
		super();
	}

	public KeyWodkTransient(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		String path = "D:" + File.separator + "object.txt";
		File file = new File(path);
		
		KeyWodkTransient transientDemo = new KeyWodkTransient("name", "password");

		ObjectOutput output = new ObjectOutputStream(new FileOutputStream(file));
		output.writeObject(transientDemo);
		
		ObjectInput input = new ObjectInputStream(new FileInputStream(file));
		KeyWodkTransient demo = (KeyWodkTransient) input.readObject();
		
		System.out.println(demo.getName() +" =======> "+ demo.getPassword());
	}

}