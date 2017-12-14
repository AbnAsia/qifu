package org.qifu.common.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

public class OpenNLPNounPhraseExtractor {
	
	public static void main(String args[]) throws Exception {
		
		TokenizerModel tm = new TokenizerModel(new FileInputStream(new File("/home/test/TestNLP/en-token.bin")));
		TokenizerME wordBreaker = new TokenizerME(tm);
		POSModel pm = new POSModel(new FileInputStream(new File("/home/test/TestNLP/en-pos-maxent.bin")));
		POSTaggerME posme = new POSTaggerME(pm);
		InputStream modelIn = new FileInputStream("/home/test/TestNLP/en-chunker.bin");
		ChunkerModel chunkerModel = new ChunkerModel(modelIn);
		ChunkerME chunkerME = new ChunkerME(chunkerModel);
		//this is your sentence
		String sentence = "Barack Hussein Obama II  is the 44th President of the United States, and the first African American to hold the office.";
		//words is the tokenized sentence
		String[] words = wordBreaker.tokenize(sentence);
		//posTags are the parts of speech of every word in the sentence (The chunker needs this info of course)
		String[] posTags = posme.tag(words);
		//chunks are the start end "spans" indices to the chunks in the words array
		Span[] chunks = chunkerME.chunkAsSpans(words, posTags);
		//chunkStrings are the actual chunks
		String[] chunkStrings = Span.spansToStrings(chunks, words);
		for (int i = 0; i < chunks.length; i++) {
			String np = chunkStrings[i];
			System.out.println(np);
			if (np.indexOf("Obama") > -1) {
				System.out.println("* ["+np+"]");
			}
		}	
		
	}

}