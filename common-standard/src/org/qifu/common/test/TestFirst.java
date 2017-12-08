package org.qifu.common.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class TestFirst {
	
	private InputStream modelIn;
	
	public TestFirst() {
		
	}
	
	public static TestFirst build() {
		return new TestFirst();
	}	
	
	public TestFirst modelIsSource(String src) throws Exception {
		this.modelIn = new FileInputStream(src);
		return this;
	}
	
	public void test() throws IOException {
		SentenceModel model = new SentenceModel(modelIn);
		SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
		String sentences[] = sentenceDetector.sentDetect("  First sentence! Second sentence! Play123! 羞恥PLAY");
		for (int i=0; sentences!=null && i<sentences.length; i++) {
			System.out.println("sentences[" + i + "]=" + sentences[i]);
		}
	}
	
	public InputStream getModelIn() {
		return modelIn;
	}

	public void setModelIn(InputStream modelIn) {
		this.modelIn = modelIn;
	}

	public static void main(String args[]) throws Exception {
		TestFirst.build().modelIsSource("/home/test/TestNLP/en-sent.bin").test();
	}
	
}
