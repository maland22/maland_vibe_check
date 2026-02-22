import React, { useEffect, useState } from "react";
import {
    Grid,
    CircularProgress,
    Typography,
    Select,
    MenuItem,
    FormControl,
    InputLabel,
    Box
} from "@mui/material";
import { useStore } from "../store/useStore";
import EventCard from "../components/EventCard";

const EventsPage: React.FC = () => {

    const { events, fetchEvents, loading, error } = useStore();
    const [sortBy, setSortBy] = useState<string>("date");

    useEffect(() => {
        fetchEvents();
    }, []);

    const sortedEvents = [...events].sort((a, b) => {
        switch (sortBy) {
            case "title":
                return a.title.localeCompare(b.title);
            case "location":
                return a.location.localeCompare(b.location);
            case "rating":
                return (b.averageRating ?? 0) - (a.averageRating ?? 0);
            case "date":
            default:
                return new Date(a.eventDate).getTime() - new Date(b.eventDate).getTime();
        }
    });

    if (loading) return <CircularProgress sx={{ m: 4 }} />;

    if (error) return <Typography color="error">{error}</Typography>;

    return (
        <Box sx={{ p: 3 }} marginTop={10}>
            <FormControl sx={{ mb: 3, minWidth: 200 }}>
                <InputLabel>Sort By</InputLabel>
                <Select
                    value={sortBy}
                    label="Sort By"
                    onChange={(e) => setSortBy(e.target.value)}
                >
                    <MenuItem value="date">Date</MenuItem>
                    <MenuItem value="title">Title</MenuItem>
                    <MenuItem value="location">Location</MenuItem>
                    <MenuItem value="rating">Average Rating</MenuItem>
                </Select>
            </FormControl>

            <Grid container spacing={3}>
                {sortedEvents.map((event, index) => (
                    <Grid xs={4}  key={event.id}>
                        <EventCard event={event} index={index} />
                    </Grid>
                ))}
            </Grid>
        </Box>
    );
};

export default EventsPage;