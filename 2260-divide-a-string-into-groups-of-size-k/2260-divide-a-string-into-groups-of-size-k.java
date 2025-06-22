class Solution {
    public String[] divideString(String s, int k, char fill) {
        int l =(s.length()/k)+1;
        if(s.length() % k == 0) l = s.length()/k;
        String[]res = new String[l];
        for(int i=0;i<=res.length;i++){
            if(s.length()>=k){
                res[i]=s.substring(0,k);
                s=s.substring(k);
            }
        }
        if (s.length()>=1){
            res[l-1]=s;
            for(int i=s.length();i<k;i++){
                res[l-1]=res[l-1]+fill;
            }
        }
        return res;
    }
}