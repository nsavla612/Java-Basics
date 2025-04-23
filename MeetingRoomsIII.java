
/*
Docusign Online Tech / Phone Screen -
Assume you have a list of meetings and list of rooms.
Each room has a type and max capacity.
A room can be a small Room with only 10 max capacity.
A room can be a medium Room with only 25 max capacity.
A room can be a high Room with only 50 max capacity.

You need to assign a room to each meeting.
We cannot assign a room if the participants in the meetings is more than the room's capacity.
We cannot assign a room if there is an ongoing meeting in that room.
We would like to assign as many rooms as possible.
For each successful room assignment, print an error message.
If no room is possible, then print an error message.

 */
import java.util.Arrays;
import java.util.List;

public class MeetingRooms3 {

    public static void main( String[] args) {
        Room[] rooms = createRooms();

        for(int i = 0 ; i < rooms.length ; i++) {
            System.out.println(rooms[i]);
        }

        Meeting[] meetings = createMeetings();

        for(int i = 0 ; i < meetings.length ; i++) {
            System.out.println(meetings[i]);
        }

        scheduleMeetings(rooms, meetings);
    }

    public static void scheduleMeetings( Room[] rooms, Meeting[] meetings) {
        RoomWithTimings[] meetingRooms = getAllRooms(rooms);
        for(Meeting currMeeting : meetings) {
            boolean isRoomAllocated = false;
            for(RoomWithTimings meetingRoom : meetingRooms) {
                if(currMeeting.participants > meetingRoom.capacity) continue;
                if(isMeetingRoomUnavailableDuringTime( currMeeting , meetingRoom.times)) continue;

                long[] time = { currMeeting.startTime, currMeeting.endTime };
                meetingRoom.times.add(time);
                meetingRoom.times.sort((a, b) -> Math.toIntExact(a[0] - b[0]));
                isRoomAllocated = true;
                System.out.println(" For meeting- " + currMeeting.meetingId + " Room allocated is- " + meetingRoom.roomId );
                break;
            }

            if(!isRoomAllocated) {
                System.out.println(" No room was allocated for meeting " + currMeeting.meetingId );
            }

        }
    }

    public static boolean isMeetingRoomUnavailableDuringTime(Meeting currMeeting, List<long[]> times) {
        for(long[] time : times) {
            if(currMeeting.startTime <= time[1]) return true;
        }
        return false;
    }

    public static  RoomWithTimings[] getAllRooms( Room[] rooms ) {
        RoomWithTimings[] sortedRooms = new RoomWithTimings[rooms.length];
        Arrays.sort(rooms, (a,b) -> a.type.ordinal() - b.type.ordinal());
        for (int i = 0 ; i < rooms.length ; i++) {
            sortedRooms[i] = new RoomWithTimings(rooms[i].roomId, rooms[i].type);
        }
        return sortedRooms;

    }

    public static Meeting[] createMeetings() {
        Meeting[] meetings = new Meeting[12];
        meetings[0] = new Meeting( " meeting0" , 28 , 10 , 20);
        for(int i = 1 ; i < 6 ; i++) {
          meetings[i] = new Meeting( " meeting" + i , 8 , 10 , 20);
        }
        for(int i = 6 ; i < 9 ; i++) {
            meetings[i] = new Meeting( " meeting" + i , 18 , 10 , 20);
        }
        meetings[9] = new Meeting( " meeting9" , 28 , 10 , 20);
        meetings[10] = new Meeting( " meeting10" , 58 , 10 , 20);
        meetings[11] = new Meeting( " meeting11" , 8 , 6 , 8);
        return  meetings;
    }

    public static Room[] createRooms() {
        Room[] rooms = new Room[9];
        for(int i = 0 ; i < 3 ; i++) {
            rooms[i * 3 ] = new Room("room" + (3 * i), RoomType.HIGH);
            rooms[i * 3 + 1] = new Room("room" + (3 * i + 1), RoomType.SMALL);
            rooms[i * 3 + 2] = new Room("room" + (3 * i + 2), RoomType.MEDIUM);
        }
        return  rooms;
    }
}

 class RoomWithTimings {
    public String roomId;
    public List<long[]> times;
    public int capacity;
    public RoomWithTimings(String roomId, RoomType type) {
        this.roomId = roomId;
        times = new ArrayList<>();
        capacity = (type == RoomType.SMALL) ? 10 : (type == RoomType.MEDIUM) ? 25 : 50;
    }
}

class Meeting {
    public String meetingId;
    public int participants;
    public long startTime;
    public long endTime;
    public Meeting(String id, int participants, long startTime, long endTime) {
        this.meetingId = id;
        this.participants = participants;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return " meeting - " + meetingId;
    }
}

class Room {
    public String roomId;
    public int capacity;
    public RoomType type;
    Room(String roomId, RoomType type) {
        this.roomId = roomId;
        this.type = type;
        capacity = (type == RoomType.SMALL) ? 10 : (type == RoomType.MEDIUM) ? 25 : 50;
    }

    @Override
    public String toString() {
        return " room- " + roomId;
    }
}

enum RoomType {SMALL , MEDIUM, HIGH }
