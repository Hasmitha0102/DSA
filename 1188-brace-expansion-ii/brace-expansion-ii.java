class Solution {
    public List<String> braceExpansionII(String expression) {
        Deque<Object[]> stack = new ArrayDeque<>();
        Set<String> currentUnion = new HashSet<>();
        List<Set<String>> currentGroup = new ArrayList<>();
        int i=0;
        int n = expression.length();
        while(i < n){
            char c = expression.charAt(i);
            if(Character.isLowerCase(c)){
                StringBuilder sb = new StringBuilder();
                while(i<n && Character.isLowerCase(expression.charAt(i))){
                    sb.append(expression.charAt(i));
                    i++;
                }
                currentGroup.add(Collections.singleton(sb.toString()));
            }
            else if(c == '{'){
                stack.push(new Object[]{currentUnion, currentGroup});
                currentUnion = new HashSet<>();
                currentGroup = new ArrayList<>();
                i++;
            }
            else if(c == ','){
                currentUnion.addAll(cartesianProduct(currentGroup));
                currentGroup = new ArrayList<>();
                i++;
            }
            else if(c == '}'){
                    currentUnion.addAll(cartesianProduct(currentGroup));
                Set<String> closedGroupResult = currentUnion;
                Object[] prev = stack.pop();
                currentUnion = (Set<String>) prev[0];
                currentGroup = (List<Set<String>>) prev[1];
                currentGroup.add(closedGroupResult);
                i++;
            }
        }
        currentUnion.addAll(cartesianProduct(currentGroup));
        List<String> result = new ArrayList<>(currentUnion);
        Collections.sort(result);
        return result;
    }
    private Set<String> cartesianProduct(List<Set<String>> group){
        if(group.isEmpty()) return Collections.emptySet();
        Set<String> result = new HashSet<>();
        result.add("");
        for(Set<String> set : group){
            Set<String> nextResult = new HashSet<>();
            for(String prefix : result){
                for(String suffix : set){
                    nextResult.add(prefix + suffix);
                }
            }
            result = nextResult;
        }
        return result;
    }
}