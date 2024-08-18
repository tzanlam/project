import api from "./api";

const RoomService = {
    getRoom(page, size){
        return api.get(`getAllRoom/${page}/${size}`)
    },
    createRoom(body){
        return api.post("createRoom", body)
    },
    updateRoom(id, body){
        return api.put("updateRoom/"+id, body)
    },
    deleteRoom(id){
        return api.delete("deleteRoom/"+id)
    }
}
export default RoomService;