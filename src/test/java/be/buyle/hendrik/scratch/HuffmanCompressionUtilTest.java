package be.buyle.hendrik.scratch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HuffmanCompressionUtilTest {

    @Test
    void roundTripCompressionRestoresOriginalString() {
        HuffmanCompressionUtil util = new HuffmanCompressionUtil();
        String input = "Lorem ipsum dolor sit amet.";
        HuffmanTree tree = util.buildTree(input.toCharArray());
        String compressed = util.compress(tree, input);
        String output = util.decompress(tree, compressed);
        assertEquals(input, output);
    }
}
