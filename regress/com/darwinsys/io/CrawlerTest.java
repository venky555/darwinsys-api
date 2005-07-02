package io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import com.darwinsys.io.Crawler;
import com.darwinsys.io.FileHandler;

import junit.framework.TestCase;

public class CrawlerTest extends TestCase {
	
	boolean seenAnyFiles = false;
	
	public void testPubCrawl() throws Exception {
		String dir =  "." ;
				
		FilenameFilter javaFileFilter = new FilenameFilter() {
			public boolean accept(File dir, String s) {
				if (s.endsWith(".java") || s.endsWith(".class") || s.endsWith(".jar"))
					return true;
				// others: projects, ... ?
				return false;
			}
		};
		FileHandler dummyVisitorJustPrints = new FileHandler() {

			public void visit(File f) {
				seenAnyFiles = true;
				System.out.println(f.getAbsolutePath());
			}

			public void init() throws IOException {
			}

			public void destroy() throws IOException {
			}
			
		};
		new Crawler(javaFileFilter, dummyVisitorJustPrints).crawl(new File(dir));
		
		assertTrue("crawler found at least one file in .", seenAnyFiles);
	}
}
