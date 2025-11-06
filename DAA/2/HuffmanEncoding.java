import java.util.*;


class Node implements Comparable<Node> {
    char ch;
    int freq;
    Node left, right;

    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    Node(int freq, Node left, Node right) {
        this.ch = '-';
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public int compareTo(Node other) {
        return this.freq - other.freq;
    }
}

public class HuffmanEncoding  {


    static void printCodes(Node root, String code, Map<Character, String> huffmanCode) {
        if (root == null)
            return;

       
        if (root.left == null && root.right == null) {
            huffmanCode.put(root.ch, code);
        }

        printCodes(root.left, code + "0", huffmanCode);
        printCodes(root.right, code + "1", huffmanCode);
    }

   
    static void huffmanEncode(String text) {
       
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : text.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

     
        PriorityQueue<Node> pq = new PriorityQueue<>();

      
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node(left.freq + right.freq, left, right);
            pq.add(parent);
        }

      
        Node root = pq.peek();

        
        Map<Character, String> huffmanCode = new HashMap<>();
        printCodes(root, "", huffmanCode);

        
        System.out.println("Character\tFrequency\tHuffman Code");
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            System.out.println(entry.getKey() + "\t\t" + entry.getValue() + "\t\t" + huffmanCode.get(entry.getKey()));
        }

        
        StringBuilder encoded = new StringBuilder();
        for (char ch : text.toCharArray()) {
            encoded.append(huffmanCode.get(ch));
        }
        System.out.println("\nEncoded Text: " + encoded);
    }

    public static void main(String[] args) {
        String text = "huffman encoding example";
        System.out.println("Original Text: " + text + "\n");
        huffmanEncode(text);
    }
}
