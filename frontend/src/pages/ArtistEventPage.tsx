import React, { useEffect } from "react";
import { useParams } from "react-router-dom";
import { Grid, CircularProgress, Typography, Box } from "@mui/material";
import { useStore } from "../store/useStore";
import EventCard from "../components/EventCard";

const ArtistEventsPage: React.FC = () => {
    const { id } = useParams<{ id: string }>();
    const { events, fetchEventsByArtist, artists, fetchArtists, loading } = useStore();

    // Artist-Name aus store ableiten
    const artist = artists.find(a => a.id === Number(id));
    const artistName = artist ? `${artist.firstName} ${artist.lastName}` : "Artist";

    // Artist-Daten laden, falls noch nicht da
    useEffect(() => {
        if (artists.length === 0) fetchArtists();
    }, []);

    // Events für Artist laden
    useEffect(() => {
        if (id) fetchEventsByArtist(Number(id));
    }, [id]);

    if (loading) return <CircularProgress sx={{ m: 4 }} />;

    return (
        <Box sx={{ p: 3 }}>
            <Typography variant="h4" sx={{ mb: 3 }}>
                Events von {artistName}
            </Typography>

            {events.length === 0 ? (
                <Typography>No events found for this artist.</Typography>
            ) : (
                <Grid container spacing={3}>
                    {events.map((event, index) => (
                        <Grid item xs={12} md={4} key={event.id}>
                            <EventCard event={event} index={index} />
                        </Grid>
                    ))}
                </Grid>
            )}
        </Box>
    );
};

export default ArtistEventsPage;
