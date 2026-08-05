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
    int c = 0;
    public int countDominantNodes(TreeNode root) {
        c=0;
        dfs(root);
        return c;
    }
    private int dfs(TreeNode node){
        if(node == null) return Integer.MIN_VALUE;
        int lm = dfs(node.left);
        int rm = dfs(node.right);
        int max = Math.max(node.val,Math.max(lm,rm));
        if(node.val == max) c++;
        return max;
    }
}