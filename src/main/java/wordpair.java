import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

public class wordpair implements Writable, WritableComparable<wordpair> {
	private Text word, filename;

	public wordpair(Text word, Text filename) { this.word = word; this.filename = filename; }
	public wordpair(String word, String filename) { this(new Text(word),new Text(filename)); }
	public wordpair() { this.word = new Text(); this.filename = new Text(); }

	public static wordpair read(DataInput in) throws IOException {
		wordpair pair = new wordpair();
		pair.readFields(in);
		return pair;
	}

	public void write(DataOutput out) throws IOException {
		word.write(out);
		filename.write(out);
	}

	public void readFields(DataInput in) throws IOException {
		word.readFields(in);
		filename.readFields(in);
	}

	public String toString() { return "{word=["+word+"]"+ " filename=["+filename+"]}"; }

	public int compareTo(wordpair other) {						// A compareTo B
		int returnVal = this.word.compareTo(other.getWord());	// return -1: A < B
		if(returnVal != 0)										// return 0: A = B
			return returnVal;									// return 1: A > B
		return this.filename.compareTo(other.getFilename());
	}
	
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		wordpair pair = (wordpair) o;

		if (word != null ? !word.equals(pair.word) : pair.word != null) return false;
		if (filename != null ? !filename.equals(pair.filename) : pair.filename != null) return false;

		return true;
	}

	public int hashCode() {
		int result = (word != null) ? word.hashCode() : 0;
		return 163 * result + ((filename != null) ? filename.hashCode() : 0);
	}

	public wordpair set(String word, String filename) {this.word.set(word); this.filename.set(filename); return this; }
	public wordpair setWord(String word){ this.word.set(word); return this; }
	public wordpair setFilename(String filename){ this.filename.set(filename); return this; }
	public Text getWord() { return word; }
	public Text getFilename() { return filename; }
}