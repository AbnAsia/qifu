package org.qifu.common.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

public class ThreeTest {
	
	private InputStream modelInToken;
	private InputStream modelIn;
	
	public ThreeTest() {
		
	}
	
	public static ThreeTest build() {
		return new ThreeTest();
	}	
	
	public ThreeTest modelTokenIsSource(String src) throws Exception {
		this.modelInToken = new FileInputStream(src);
		return this;
	}	
	
	public ThreeTest modelIsSource(String src) throws Exception {
		this.modelIn = new FileInputStream(src);
		return this;
	}
	
	public void test() throws IOException {
		
		String sentence = 
				"The Bayview Bistro, Known as one of the most romantic dining spots in the Bay area, " 
				+ "The Bayview Bistro serves outstanding food at affordable prices in a truly relaxing (1) . " 
				+ "All dishes are made from (2) sourced from local farmers and fisherman. Head chef, Mark Romano, "
				+ "is well-known for creating delicious (3) that highlight the freshness of these local ingredients. " 
				+ "The prices at the Bayview Bistro are definitely reasonable considering the generous "  
				+ "(4) the restaurant serves up. For anyone visiting the Bay Area or for couples looking for a quiet night on the town, "
				+ "the Bayview Bistro is definitely one of my top (5) .";
		
		TokenizerModel modelToken = new TokenizerModel(modelInToken);
		Tokenizer tokenizer = new TokenizerME(modelToken);
		String tokens[] = tokenizer.tokenize( sentence );
		
		for (int i=0; null != tokens && i<tokens.length; i++) {
			System.out.println("tokens["+i+"]: "+tokens[i]);
		}
		
		TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
		NameFinderME nameFinder = new NameFinderME(model);
		Span names[] = nameFinder.find( tokens );
		
		double[] spanProbs = nameFinder.probs(names);
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; names!=null && i<names.length; i++) {
			for (int si = names[i].getStart(); si < names[i].getEnd(); si++) {
				sb.append("Probs: [").append(spanProbs[i]).append("] \t Value: [").append(tokens[si]).append("]\n");
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
		String modelTokenBin = "en-token.bin";
		String modelBin = "en-ner-person.bin"; // mymodel.bin
		ThreeTest.build()
			.modelTokenIsSource("/home/test/TestNLP/"+modelTokenBin)
			.modelIsSource("/home/test/TestNLP/"+modelBin)
			.test();
		
	}
	
}
