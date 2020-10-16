package com.blz.employeepayroll.tester;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

public class NIOFileAPITest {
	private static String HOME = System.getProperty("user.home");
	private static String PLAY_WITH_NIO = "TempPlayGround";

	@Test
	public void givenPathWhenCheckedThenConfirm() throws IOException {
		// Checking if files exists
		Path homePath = Paths.get(HOME);
		Assert.assertTrue(Files.exists(homePath));

		// Delete files and check File not Exist
		Path playPath = Paths.get(HOME + "/" + PLAY_WITH_NIO);
		if (Files.exists(playPath))
			FilesUtils.deleteFiles(playPath.toFile());
		Assert.assertTrue(Files.notExists(playPath));

		// Create Directory
		Files.createDirectory(playPath);
		Assert.assertTrue(Files.exists(playPath));

		// Create Files
		IntStream.range(0, 10).forEach(counter -> {
			Path tempFile = Paths.get(playPath + "/temp" + counter);
			Assert.assertTrue(Files.notExists(tempFile));
			try {
				Files.createFile(tempFile);

			} catch (IOException e) {
				Assert.assertTrue(Files.exists(tempFile));
			}
		});

		// List Files, Directories as well as Files with extension
		Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
		Files.newDirectoryStream(playPath).forEach(System.out::println);
		Files.newDirectoryStream(playPath, path -> path.toFile().isFile() && path.toString().startsWith("temp"))
				.forEach(System.out::println);
	}
}