import api from "./api";

const BookingService = {
    getBooking(page, size){
        return api.get(`getAllBooking/${page}/${size}`)
    },
    createBooking(body){
        return api.post("createBooking", body)
    },
    updateBooking(id, body){
        return api.put("updateBooking/"+id, body)
    },
    deleteBooking(id){
        return api.delete("deleteBooking/"+id)
    }
}

export default BookingService;