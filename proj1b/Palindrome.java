public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> dq = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++){
            dq.addLast(word.charAt(i));
        }
        return dq;
    }
    public boolean isPalindrome(String word){
        Deque<Character> dq = wordToDeque(word);
        boolean flag = true;
        while (!dq.isEmpty()){
            Character f = dq.removeFirst();
            Character l = dq.removeLast();
            if (l != f && l != null  && f != null){
                flag = false;
                break;
            }
        }
        return flag;
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> dq = wordToDeque(word);

        if (word.length() == 1 || word.length() == 0){
            return true;
        }
        else {
            Character f = dq.removeFirst();
            Character l = dq.removeLast();
            return cc.equalChars(f,l) && isPalindrome(word.substring(1,word.length()-1), cc);
        }
    }

    /*private String DequetoWord(){

    }*/
    /*public static void main(String[] args){
        Palindrome p = new Palindrome();
        Deque<Character> dq = p.wordToDeque("a");
        dq.removeLast();
    }*/
}
