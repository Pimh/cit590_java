package cipher;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Caesar { 
		
		public static void main(String[] args) {    
	    	new Caesar().main();
	    }
		
		void main() {
			int shift = 15;
			String plainText = "Sigh... the holiday is over :(";
			System.out.println("Original msg: " + plainText);
			String cipheredText = encipher(shift, plainText); 
			System.out.println("Ciphered msg: " + cipheredText);
			
			String decipheredText = decipher(cipheredText);
			System.out.println("Deciphered msg: " + decipheredText);
			
		}

		public String encipher(int shift, String plainText) {
			
			String enc_text = "";
			// Loop through each letter of the text
			for (int i = 0; i < plainText.length(); i++) {
				// Check if the current character is a letter
				char ch = plainText.charAt(i);
				if (Character.isLetter(ch)) {
					char enc_letter = shiftLetter(shift, ch);
					enc_text = enc_text.concat(Character.toString(enc_letter));
				} else {
					enc_text = enc_text.concat(plainText.substring(i, i+1));
				}
			}
			
			return enc_text;			
		}
		
		public char shiftLetter(int shift, char letter) {
			// a = 97, z = 122, A = 65, Z = 90
		
			// Calculate new integer value & Implement end around
			int shift_letter = (int)letter;
			if (Character.isLowerCase(letter)) {
				shift_letter = ((letter + shift - 97) % 26 ) + 97;
			} else if (Character.isUpperCase(letter)) {
				shift_letter = ((letter + shift - 65) % 26 ) + 65;
			}

			// Convert integer to char
			char enc_letter = (char)shift_letter;
			return enc_letter; 
		}

		public String decipher(String cipheredText) {
			
			Set<String> dictionary = createDictionary();
			
			// Loop through all possible shift values
			double max = 0;
			int bestShift = 0;
			int tie = 0;
			for (int deShift = 1; deShift < 26; deShift++) {
				String shiftText = shiftString(deShift, cipheredText);
				
				// Split string
				String letterText = shiftText.replaceAll("\\W", " ");
				String[] texts = letterText.split(" ");

				// Check if these shifted texts are words
				double count = 0;
				for (String text: texts) {
					if (isWord(text, dictionary)) {
						count++;
					}
				}
				double wordFraction = count/texts.length;
				// System.out.println(wordFraction);
				if (wordFraction > max) {
					bestShift = deShift; 
					max = wordFraction; 
					tie = 0;
				} else if (wordFraction == max) {
					tie++;
				}		
			}
			
			// Return the deciphered text at the shift value with highest word count
			if (tie > 0) {
				System.out.println("There is more than one possible meaningfull "
						+ "deciphered text");
				return shiftString(bestShift, cipheredText); 
			} else {
				return shiftString(bestShift, cipheredText);
			}		
		}
		
		public boolean isWord(String text, Set<String> dictionary) {
							
			// Check if this text is a word
			return dictionary.contains(text.trim().toLowerCase());

		}
		
		public Set<String> createDictionary() {
			// Read in words from the dictionary file
			FileReader fileReader = null;
			try { fileReader = new FileReader("../wordsEN.txt"); }
			catch (FileNotFoundException e) {
				System.err.println("Cannot find input file: " + "wordsEN.txt");
				e.printStackTrace( ); 
			}
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			boolean read = true;
			Set<String> dictionary = new HashSet<String>();
			while (read) {
				try { 
					String line = bufferedReader.readLine(); 
					if (line != null) {
						dictionary.add(line.trim());
					} else { 
						read = false;
					}					
				} catch (IOException e) {
					e.printStackTrace();
					read = false;
				}
			}
			try { bufferedReader.close(); }
			catch(IOException e) { }

			try {fileReader.close(); }
			catch(IOException e) {}
			
			return dictionary;
		}
		
		public String shiftString(int deShift, String cipheredText) {
			String deci_text = "";
			// Loop through each letter of the text
			for (int i = 0; i < cipheredText.length(); i++) {
				// Check if the current character is a letter
				char ch = cipheredText.charAt(i);
				if (Character.isLetter(ch)) {
					char deci_letter = deShiftLetter(deShift, ch);
					deci_text = deci_text.concat(Character.toString(deci_letter));
				} else {
					deci_text = deci_text.concat(cipheredText.substring(i, i+1));
				}
			}
			
			return deci_text;
		}
		
		public char deShiftLetter(int deShift, char letter) {
			int shift_letter = (int)letter;

			if (Character.isLowerCase(letter)) {
				shift_letter = ((letter - deShift -97 + 26) % 26 ) + 97;
			} else if (Character.isUpperCase(letter)) {
				shift_letter = ((letter - deShift -65 + 26) % 26 ) + 65;
			}

			// Convert integer to char
			char deci_letter = (char)shift_letter;
			return deci_letter; 
		}

}
