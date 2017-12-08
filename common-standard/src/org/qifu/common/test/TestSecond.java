package org.qifu.common.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

public class TestSecond {
	private InputStream modelIn;
	
	public TestSecond() {
		
	}
	
	public static TestSecond build() {
		return new TestSecond();
	}	
	
	public TestSecond modelIsSource(String src) throws Exception {
		this.modelIn = new FileInputStream(src);
		return this;
	}
	
	public void test() throws IOException {
		String tokens[] = "Britney Spears was reunited with her sons monday, George Miller how are you. dan fuck. Natalie and Bill with me get money. we do JOB with Michael Hinterhofer, Peter Schubert, Bruno Schulz at CA, USA.".split("\\ ");
		for (int i=0; null != tokens && i<tokens.length; i++) {
			System.out.println("tokens["+i+"]=" + tokens[i]);
		}
		TokenNameFinderModel nameFinderModel = new TokenNameFinderModel(this.modelIn);
		NameFinderME nameFinder = new NameFinderME(nameFinderModel);
		Span names[] = nameFinder.find( tokens );
		StringBuilder sb = new StringBuilder();
		for (int i=0; names!=null && i<names.length; i++) {
			for (int si = names[i].getStart(); si < names[i].getEnd(); si++) {
				sb.append(tokens[si]).append("\n");
			}
		}
		System.out.println("result:\n"+sb.toString());
	}
	
	public InputStream getModelIn() {
		return modelIn;
	}

	public void setModelIn(InputStream modelIn) {
		this.modelIn = modelIn;
	}

	public static void main(String args[]) throws Exception {
		String modelBin = "en-ner-person.bin"; // mymodel.bin
		TestSecond.build().modelIsSource("/home/test/TestNLP/"+modelBin).test();
	}
	
}
