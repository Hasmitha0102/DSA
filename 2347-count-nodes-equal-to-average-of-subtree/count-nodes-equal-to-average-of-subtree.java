/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int count = 0;
    public int averageOfSubtree(TreeNode root) {
        count = 0;
        postorder(root);
        return count;
    }
    private int[] postorder(TreeNode node){
        if(node == null) return new int[]{0, 0};
        int[] left = postorder(node.left);
        int[] right = postorder(node.right);
        int currsum = left[0] + right[0] + node.val;
        int currcount = left[1] + right[1] + 1;
        if(currsum/currcount == node.val) count++;
        return new int[]{currsum, currcount};
    }
}