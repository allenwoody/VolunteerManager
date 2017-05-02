package com.allen.core.util;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class CharArrayWriter extends Writer {
	private static final char[] EMPTY_CHAR_ARRAY = new char[0];
	private int bufferEntrySize = 1024;
	private List<char[]> buffers = new ArrayList();
	private int currentBufferIndex;
	private char[] currentBuffer;
	private int count;
	private Turn turn = new Turn(10);

	public CharArrayWriter() {
		needNewBuffer();
	}

	public CharArrayWriter(int bufferEntrySize) {
		this.bufferEntrySize = bufferEntrySize;
	}

	public int getBufferEntrySize() {
		return this.bufferEntrySize;
	}

	private char[] getBuffer(int index) {
		return (char[]) this.buffers.get(index);
	}

	private int getCurrentBufferPos() {
		if (this.count == 0) {
			return 0;
		}
		int pos = this.count % this.bufferEntrySize;
		if (pos == 0) {
			needNewBuffer();
			return 0;
		}
		return pos;
	}

	private void needNewBuffer() {
		if (this.currentBufferIndex < this.buffers.size() - 1) {
			this.currentBufferIndex += 1;
			this.currentBuffer = getBuffer(this.currentBufferIndex);
		} else {
			char[] createOne = new char[this.bufferEntrySize];
			this.buffers.add(createOne);
			this.currentBuffer = createOne;
			this.currentBufferIndex += 1;
		}
	}

	public void write(char[] cbuf, int off, int len) throws IOException {
		if ((off < 0) || (off > cbuf.length) || (len < 0) || (off + len > cbuf.length) || (off + len < 0)) {
			throw new IndexOutOfBoundsException();
		}
		if (len == 0) {
			return;
		}
		int remaining = len;
		int inBufferPos = getCurrentBufferPos();
		while (remaining > 0) {
			int part = Math.min(remaining, this.bufferEntrySize - inBufferPos);
			System.arraycopy(cbuf, off + len - remaining, this.currentBuffer, inBufferPos, part);
			remaining -= part;
			this.count += part;
			if (remaining > 0) {
				inBufferPos = getCurrentBufferPos();
			}
		}
	}

	public void write(int b) {
		int inBufferPos = getCurrentBufferPos();
		this.currentBuffer[inBufferPos] = ((char) b);
		this.count += 1;
	}

	public int size() {
		return this.count;
	}

	public void reset() {
		int leftBufferCount = this.turn.addCountGetAvg();
		this.count = 0;
		this.currentBufferIndex = 0;
		this.currentBuffer = getBuffer(0);
		curtailBuffer(leftBufferCount);
	}

	private void curtailBuffer(int n) {
		if (n < 1) {
			return;
		}
		if (this.buffers.size() <= n) {
			return;
		}
		for (int i = this.buffers.size() - 1; i >= n; i--) {
			this.buffers.remove(i);
		}
	}

	public void writeTo(Writer out) throws IOException {
		int remaining = this.count;
		for (int i = 0; i < this.buffers.size(); i++) {
			char[] buf = getBuffer(i);
			int c = Math.min(buf.length, remaining);
			out.write(buf, 0, c);
			remaining -= c;
			if (remaining == 0) {
				break;
			}
		}
	}

	public char[] toCharArray() {
		int remaining = this.count;
		if (remaining == 0) {
			return EMPTY_CHAR_ARRAY;
		}
		char[] newbuf = new char[remaining];
		int pos = 0;
		for (int i = 0; i < this.buffers.size(); i++) {
			char[] buf = getBuffer(i);
			int c = Math.min(buf.length, remaining);
			System.arraycopy(buf, 0, newbuf, pos, c);
			pos += c;
			remaining -= c;
			if (remaining == 0) {
				break;
			}
		}
		return newbuf;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(this.count);
		int remaining = this.count;
		for (int i = 0; i < this.buffers.size(); i++) {
			char[] buf = getBuffer(i);
			int c = Math.min(buf.length, remaining);
			sb.append(buf, 0, c);
			remaining -= c;
			if (remaining == 0) {
				break;
			}
		}
		return sb.toString();
	}

	public void flush() throws IOException {
	}

	public void close() throws IOException {
	}

	private final class Turn {
		private CharArrayWriter.Fragment currentFg;

		private Turn(int size) {
			CharArrayWriter.Fragment head = new CharArrayWriter.Fragment();
			this.currentFg = head;
			for (int i = 0; i < size - 1; i++) {
				this.currentFg.next = new CharArrayWriter.Fragment();
				this.currentFg = this.currentFg.next;
			}
			this.currentFg.next = head;
		}

		private int addCountGetAvg() {
			if (CharArrayWriter.this.count != 0) {
				this.currentFg.writeCount = CharArrayWriter.this.count;
				this.currentFg = this.currentFg.next;
			}
			long total = 0L;
			int usedFragment = 0;
			CharArrayWriter.Fragment f = this.currentFg.next;
			while (f != this.currentFg) {
				total += f.writeCount;
				if (f.writeCount != 0) {
					usedFragment++;
				}
				f = f.next;
			}
			if ((usedFragment == 0) || (total == 0L)) {
				return CharArrayWriter.this.count;
			}
			return (int) (total / usedFragment / CharArrayWriter.this.bufferEntrySize) + 1;
		}
	}

	private static final class Fragment {
		private int writeCount = 0;
		private Fragment next;
	}
}
