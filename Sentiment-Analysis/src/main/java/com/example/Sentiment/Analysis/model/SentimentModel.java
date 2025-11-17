/*
package com.example.Sentiment.Analysis.model;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.util.*;

public class SentimentModel {
    private static MultiLayerNetwork model;
    private static Map<String, Integer> wordIndex = new HashMap<>();

    static {
        wordIndex.put("good", 0);
        wordIndex.put("awesome", 1);
        wordIndex.put("bad", 2);
        wordIndex.put("boring", 3);
    }

    public static void train() {
        model = new MultiLayerNetwork(new NeuralNetConfiguration.Builder()
                .updater(new Adam(0.01))
                .list()
                .layer(new DenseLayer.Builder().nIn(4).nOut(8).activation(Activation.RELU).build())
                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.MCXENT)
                        .nIn(8).nOut(2).activation(Activation.SOFTMAX).build())
                .build());
        model.init();

        INDArray features = Nd4j.create(new double[][]{
                {1, 1, 0, 0}, // good + awesome
                {0, 0, 1, 1}  // bad + boring
        });

        INDArray labels = Nd4j.create(new double[][]{
                {1, 0}, // Positive
                {0, 1}  // Negative
        });

        for (int i = 0; i < 200; i++) {
            model.fit(features, labels);
        }
    }

    public static String predict(String text) {
        double[] input = new double[4];
        for (String word : text.toLowerCase().split(" ")) {
            if (wordIndex.containsKey(word)) {
                input[wordIndex.get(word)] = 1;
            }
        }
        INDArray features = Nd4j.create(input);
        INDArray output = model.output(features);
        int classIndex = Nd4j.argMax(output, 1).getInt(0);

        return classIndex == 0 ? "Positive" : "Negative";
    }

}
*/

package com.example.sentiment.analysis.model;

import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.util.HashMap;
import java.util.Map;

public class SentimentModel {

    private static MultiLayerNetwork model;
    private static Map<String, Integer> wordIndex = new HashMap<>();

    static {
        wordIndex.put("good", 0);
        wordIndex.put("awesome", 1);
        wordIndex.put("bad", 2);
        wordIndex.put("boring", 3);
    }

    public static void train() {
        model = new MultiLayerNetwork(new NeuralNetConfiguration.Builder()
                .updater(new Adam(0.01))
                .list()
                .layer(new DenseLayer.Builder().nIn(4).nOut(8).activation(Activation.RELU).build())
                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.MCXENT)
                        .nIn(8).nOut(2).activation(Activation.SOFTMAX).build())
                .build());
        model.init();

        INDArray features = Nd4j.create(new double[][]{
                {1, 1, 0, 0}, // good + awesome
                {0, 0, 1, 1}  // bad + boring
        });

        INDArray labels = Nd4j.create(new double[][]{
                {1, 0}, // Positive
                {0, 1}  // Negative
        });

        for (int i = 0; i < 200; i++) {
            model.fit(features, labels);
        }
    }

    public static String predict(String text) {
        double[] inputArr = new double[4];
        for (String word : text.toLowerCase().split(" ")) {
            if (wordIndex.containsKey(word)) {
                inputArr[wordIndex.get(word)] = 1;
            }
        }

        // ✅ Important: reshape to 2D matrix [1, 4]
        INDArray features = Nd4j.create(inputArr).reshape(1, 4);

        INDArray output = model.output(features);
        int classIndex = Nd4j.argMax(output, 1).getInt(0);

        return classIndex == 0 ? "Positive" : "Negative";
    }
}

