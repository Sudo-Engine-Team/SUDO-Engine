package site.root3287.lwjgl.net;

public enum Packet {
	PACKET_HEADER{

		@Override
		public int getID() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public byte[] getData() {
			byte[] data = {0x41, 0x42};
			return data;
		}
		
	},
	CONNECT {
		@Override
		public int getID() {
			// TODO Auto-generated method stub
			return 1;
		}

		@Override
		public byte[] getData() {
			// TODO Auto-generated method stub
			return "CONNECT".getBytes();
		}
	}, 
	DISCONNECT {
		@Override
		public int getID() {
			// TODO Auto-generated method stub
			return -1;
		}

		@Override
		public byte[] getData() {
			// TODO Auto-generated method stub
			return "DISCONNECT".getBytes();
		}
	}, 
	PING {
		@Override
		public int getID() {
			// TODO Auto-generated method stub
			return 2;
		}

		@Override
		public byte[] getData() {
			// TODO Auto-generated method stub
			return "PING".getBytes();
		}
	},
	PONG {
		@Override
		public int getID() {
			// TODO Auto-generated method stub
			return 3;
		}

		@Override
		public byte[] getData() {
			// TODO Auto-generated method stub
			return "PONG".getBytes();
		}
	}, 
	CHAT {
		@Override
		public int getID() {
			// TODO Auto-generated method stub
			return 4;
		}

		@Override
		public byte[] getData() {
			// TODO Auto-generated method stub
			return "CHAT".getBytes();
		}
	}, 
	MOVE {
		@Override
		public int getID() {
			// TODO Auto-generated method stub
			return 5;
		}

		@Override
		public byte[] getData() {
			// TODO Auto-generated method stub
			return "MOVE".getBytes();
		}
	};
	
	public abstract int getID();
	public abstract byte[] getData();
}
