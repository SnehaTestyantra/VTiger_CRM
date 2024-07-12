package randomNumber;

public class GenerateAlphaNumericRandomData {

	public static void main(String[] args) {
		int n = 20;
		String AplhaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
		StringBuilder sb = new StringBuilder(n);
		for(int i=0;i<n;i++)
		{
			int index = (int)(AplhaNumericString.length()* Math.random());
			sb.append(AplhaNumericString.charAt(index));
		}
		System.out.println(sb);

	}

}
