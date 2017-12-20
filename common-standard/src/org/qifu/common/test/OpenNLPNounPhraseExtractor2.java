package org.qifu.common.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

public class OpenNLPNounPhraseExtractor2 {
	
	public static void main(String args[]) throws Exception {
		
		TokenizerModel tm = new TokenizerModel(new FileInputStream(new File("/home/test/TestNLP/en-token.bin")));
		TokenizerME wordBreaker = new TokenizerME(tm);
		POSModel pm = new POSModel(new FileInputStream(new File("/home/test/TestNLP/en-pos-maxent.bin")));
		POSTaggerME posme = new POSTaggerME(pm);
		InputStream modelIn = new FileInputStream("/home/test/TestNLP/en-chunker.bin");
		ChunkerModel chunkerModel = new ChunkerModel(modelIn);
		ChunkerME chunkerME = new ChunkerME(chunkerModel);
		//this is your sentence
		String sentence = 
				"1. Barack Hussein Obama II  is the 44th President of the United States, and the first African American to hold the office. \n" + 
				"2. Theresa May has discussed Brexit and events in the Middle East in a pre-Christmas phone call with Donald Trump. \n" + 
				"3. Angela Dorothea Merkel is a German politician serving as Chancellor of Germany since 2005 and leader of the centre-right Christian Democratic Union (CDU) since 2000. Merkel has been widely described as the de facto leader of the European Union and the most powerful woman in the world. \n" +
				"4. Prime Minister Justin Trudeau with Mexican President Enrique Peña Nieto and U.S. President Barack Obama, June 29, 2016. \n";
		//words is the tokenized sentence
		String[] words = wordBreaker.tokenize(sentence);
		//posTags are the parts of speech of every word in the sentence (The chunker needs this info of course)
		String[] posTags = posme.tag(words);
		//chunks are the start end "spans" indices to the chunks in the words array
		Span[] chunks = chunkerME.chunkAsSpans(words, posTags);
		//chunkStrings are the actual chunks
		String[] chunkStrings = Span.spansToStrings(chunks, words);
		
		
		TokenNameFinderModel nameFinderModel = new TokenNameFinderModel( new FileInputStream(new File("/home/test/TestNLP/en-ner-person.bin")) );
		NameFinderME nameFinder = new NameFinderME(nameFinderModel);
		
		for (int i = 0; i < chunks.length; i++) {
			String np = chunkStrings[i];
			
			String tokens[] = np.split("\\ ");
			Span names[] = nameFinder.find( tokens );
			double[] spanProbs = nameFinder.probs(names);
			
			List<String> nList = new ArrayList<String>();
			
			for (int j=0; names!=null && j<names.length; j++) {
				for (int si = names[j].getStart(); si < names[j].getEnd(); si++) {
					System.out.println(spanProbs[j] + " = " + tokens[si] );
					/*
					if (spanProbs[j]>=0.85) {
						nList.add( tokens[si] );
					}
					*/
					nList.add( tokens[si] );
				}
			}
			
			boolean f = false;
			for (int k=0; k<nList.size(); k++) {
				if (np.indexOf(nList.get(k))>-1) {
					f = true;
				}
			}
			
			//System.out.println( np );
			if (f) {
				System.out.println("** find name : " + np);
			}
			
		}
		
		
	}
	
}
