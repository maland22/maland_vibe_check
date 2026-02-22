import { create } from "zustand";
import { api } from "../api/axios";
import type {Artist, Rating,Event} from "../common/model.ts";


interface Store {
    artists: Artist[];
    events: Event[];
    ratings: Rating[];
    loading: boolean;
    error: string | null;

    fetchArtists: () => Promise<void>;
    fetchEvents: () => Promise<void>;
    fetchEventsByArtist: (artistId: number) => Promise<void>;
    fetchRatings: (eventId: number) => Promise<void>;
    addRating: (eventId: number, stars: number, comment: string) => Promise<void>;
}

export const useStore = create<Store>((set) => ({
    artists: [],
    events: [],
    ratings: [],
    loading: false,
    error: null,

    fetchArtists: async () => {
        set({ loading: true });
        try {
            const res = await api.get("/artists");
            set({ artists: res.data, loading: false });
        } catch {
            set({ error: "Failed to load artists", loading: false });
        }
    },

    fetchEvents: async () => {
        set({ loading: true });
        try {
            const res = await api.get("/events");
            set({ events: res.data.content, loading: false });
        } catch {
            set({ error: "Failed to load events", loading: false });
        }
    },

    fetchEventsByArtist: async (artistId) => {
        const res = await api.get(`/events/artist/${artistId}`);
        set({ events: res.data.content });
    },

    fetchRatings: async (eventId) => {
        const res = await api.get(`/ratings/event/${eventId}`);
        set({ ratings: res.data });
    },

    addRating: async (eventId, stars, comment) => {
        await api.post(`/ratings/${eventId}`, { stars, comment });
    },
}));