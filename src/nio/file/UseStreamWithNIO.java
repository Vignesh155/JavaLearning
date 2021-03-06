package src.nio.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * Using Stream API with NIO File
 * 
 * 1.Convert BufferedReader to stream using lines() method
 * 2.NIO File Stream. Convert file to stream using Files.lines()
 * 3.Convert file to ArrayList using Files.readAllLines() method. Convert ArrayList to stream
 *
 */
public class UseStreamAPIWithNIO {

	public static void main(String[] args) {

		/**
		 * 1.Convert BufferedReader to stream using lines() method
		 */
		try (BufferedReader buffReader = new BufferedReader(new FileReader(
				"BufferedReaderToStream"))) {

			buffReader.lines().forEach(line -> System.out.println(line));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/**
		 * 2.NIO File Stream. Convert file to stream using Files.lines()
		 */
		try (Stream<String> lines = Files.lines(Paths.get(
				"NIOFileStream"))) {

			lines.forEach(line -> System.out.println(line));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/**
		 * 3.Convert file to ArrayList using Files.readAllLines() method. Convert
		 * ArrayList to stream
		 */
		Path file = Paths.get(
				"FileToArraylistToStream");
		List<String> fileListArray;

		try {

			// Convert file to ArrayList
			fileListArray = Files.readAllLines(file);

			fileListArray.stream()
					// .filter(line -> line.contains("Convert"))
					.forEach(line -> System.out.println(line));

			// Get wordcount with stream API
			long wordCount = fileListArray.stream()
					.flatMap(line -> Stream.of(line.split(" ")))
					.filter(word -> word.contains("Convert")).count();

			System.out.println("Word count: " + wordCount);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
