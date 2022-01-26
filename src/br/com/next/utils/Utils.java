package br.com.next.utils;

import java.util.Random;

public class Utils {

	
	public static int numeroRandomico(int min, int max) {

		Random random = new Random();
		int numeroRand = random.nextInt((max - min) + 1) + min;
		return numeroRand;
	}
}
