import java.util.*;

public class BTreeIndex {
	String[] myDocs;
	BinaryTree termList;
	BTNode root;
   ArrayList<String> allWords = new ArrayList<String>();
	int count=0;
	/**
	 * Construct binary search tree to store the term dictionary 
	 * @param docs List of input strings
	 * 
	 */
	public BTreeIndex(String[] docs)
	{
		//TO BE COMPLETED
	
      
		for (String doc : docs){
			String words[] = doc.split(" ");
			for(String word : words){
				if(allWords.indexOf(word)==-1){
					allWords.add(word);
				}
			}
	  }
	Collections.sort(allWords);
	
	//Construct a Binary tree from sorted list
   termList = new BinaryTree();
	ArrayList<Integer> list = new ArrayList<Integer>();
	ArrayList<ArrayList<Integer>>docLists = new ArrayList<ArrayList<Integer>>();
      int start = 0;
      int end = allWords.size() -1;
      int mid = (start + end) /2;
	  String midnode = allWords.get(mid);
      BTNode r = new BTNode(midnode,list);
	   root = r;
//   add(r, keys, start, mid-1);
	//   add(r, keys, mid+1, end);
	for (int i = 0; i < docs.length; i++) {
		String words[] = docs[i].split(" ");

	for(String word : words){
		if(termList.search(r,word) == null)
		{
			list = new ArrayList<Integer>();
			list.add(i);
			docLists.add(list);
			termList.add(r, new BTNode(word, list));
		} 
		else 
		{

			BTNode out = termList.search(r, word);
			list = out.docLists;
			if (!list.contains(i)) 
			{
				list.add(i);
			}
			out.docLists = list;
		}	
		}
	}
	
      
     //System.out.println(allWords);
	}
	
	/**
	 * Single keyword search
	 * @param query the query string
	 * @return doclists that contain the term
	 */
	public ArrayList<Integer> search(String query)
	{
			BTNode node = termList.search(root, query);
			if(node==null)
				return null;
			return node.docLists;
	}
	
	/**
	 * conjunctive query search
	 * @param query the set of query terms
	 * @return doclists that contain all the query terms
	 */
	public ArrayList<Integer> search(String[] query)
	{
		ArrayList<Integer> result = search(query[0]);
		int termId = 1;
		while(termId<query.length)
		{
			ArrayList<Integer> result1 = search(query[termId]);
			result = merge(result,result1);
			termId++;
		}		
		return result;
	}
	
	/**
	 * 
	 * @param wildcard the wildcard query, e.g., ho (so that home can be located)
	 * @return a list of ids of documents that contain terms matching the wild card
	 */
	public ArrayList<Integer> wildCardSearch(String wildcard)
	{
		//TO BE COMPLETED
		ArrayList<Integer> output = new ArrayList<Integer>();
		ArrayList<BTNode> out_bt = new ArrayList<BTNode>();
      // System.out.println(root);
      
      out_bt = termList.wildCardSearch(root, wildcard);
		if (out_bt.size() > 0) 
      {
			BTNode init = out_bt.get(count);
			output = init.docLists;
         count++;
      }
     
	
		return output;
	
	}
	
	
	private ArrayList<Integer> merge(ArrayList<Integer> l1, ArrayList<Integer> l2)
	{
		ArrayList<Integer> mergedList = new ArrayList<Integer>();
		int id1 = 0, id2=0;
		while(id1<l1.size()&&id2<l2.size()){
			if(l1.get(id1).intValue()==l2.get(id2).intValue()){
				mergedList.add(l1.get(id1));
				id1++;
				id2++;
			}
			else if(l1.get(id1)<l2.get(id2))
				id1++;
			else
				id2++;
		}
		return mergedList;
	}
	
	
	/**
	 * Test cases
	 * @param args commandline input
	 */
	public static void main(String[] args)
	{
		String[] docs = {"text warehousing over big data",
                       "dimensional data warehouse over big data",
                       "nlp before text mining",
                       "nlp before text classification"};
		//TO BE COMPLETED with testcases
	  BTreeIndex btindex = new BTreeIndex(docs);
	  
	  System.out.println("Task 1 :  Search a term from the tree index");
	  System.out.println("Result for warehouse: " + btindex.search("warehouse"));
	  System.out.println("Result for data: " + btindex.search("data"));
	  System.out.println("\n");

	  System.out.println("Task 1 :  Search conjunctive queries");
	  System.out.println("Result for over big data: " + btindex.search(new String[]{"over", "big", "data"}));
	  System.out.println("Result for before text mining: " + btindex.search(new String[]{"before", "text", "mining"}));
	  System.out.println("\n");
     
	  System.out.println("Task 2 :  Search Wildcard queries");
     System.out.println("Result for class: " + btindex.wildCardSearch("class"));
     System.out.println("Result for di: " + btindex.wildCardSearch("di"));
	  System.out.println("\n");
	}
}