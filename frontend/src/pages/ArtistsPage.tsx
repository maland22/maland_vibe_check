import React, { useEffect } from "react";
import { Grid, CircularProgress } from "@mui/material";
import { useStore } from "../store/useStore";
import ArtistCard from "../components/ArtistCard";

const ArtistsPage: React.FC = () => {

    const { artists, fetchArtists, loading } = useStore();

    useEffect(() => {
        fetchArtists();
    }, []);

    if (loading) return <CircularProgress sx={{ m: 4 }} />;

    return (
        <Grid container spacing={3} sx={{ p: 3 }}>
            {artists.map((artist, index) => (
                <Grid item xs={12} md={4} key={artist.id}>
                    <ArtistCard artist={artist} index={index} />
                </Grid>
            ))}
        </Grid>
    );
};

export default ArtistsPage;