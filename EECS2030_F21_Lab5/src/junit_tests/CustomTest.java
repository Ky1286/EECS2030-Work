package junit_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.BinarySeqOperation;
import model.OccursWithin;
import model.Projection;
import model.SeqOperation;
import model.SumsOfPrefixes;

public class CustomTest {

	@Test
	public void test_projection_01() {
		int[] seq1a = {1, 3, 5};
		int[] seq2 = {};
		
		BinarySeqOperation binOp = new Projection(seq1a, seq2);
		SeqOperation op = binOp; // 2nd sequence may contain duplicates
		assertEquals("Projecting [1, 3, 5] to [] results in: []", op.toString());

		/*
		 * You may want to also test:
		 * 	1) Projecting an empty seq1 [] over a non-empty seq2 should result in an empty sequence.
		 * 	2) Projecting a non-empty seq1 over an empty seq2 [] should result in an empty sequence.  
		 */
	}
	
	@Test
	public void test_occurs_within_01() {
		int[] seq1a = {};
		int[] seq2 = {2, 1, 6, 3, 1, 4, 5, 3};
		
		/*
		 * Does the 1st sequence appear as part of the 2nd sequence? 
		 */
		BinarySeqOperation binOp = new OccursWithin(seq1a, seq2);
		SeqOperation op = binOp;
		assertEquals("[] occurs within [2, 1, 6, 3, 1, 4, 5, 3]", op.toString());
		
		/*
		 * You may want to also test:
		 * 	1) An empty seq1 [] occurs within any seq2 (either empty or non-empty).
		 * 	2) A non-empty seq1 does not occur within an empty seq2 []. 
		 * 	3) A sequence does not occur within another shorter sequence.  
		 */
	}
	
	@Test
	public void test_sums_of_prefixes_01() {
		int[] seq1 = {};
		SeqOperation op = new SumsOfPrefixes(seq1);
		assertEquals("Sums of prefixes of [] is: []", op.toString());

		
		/*
		 * You may want to also test the case where an empty sequence has only one prefix: []  
		 */
	}
}
