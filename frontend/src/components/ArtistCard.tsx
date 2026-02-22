import React from 'react';
import { Card, CardContent, CardMedia, Typography, Button } from "@mui/material";
import { useNavigate } from "react-router-dom";
import type {Artist} from "../common/model.ts";

interface ArtistCardProps {
    artist: Artist;
    index: number;
}

const ArtistCard: React.FC<ArtistCardProps> = ({ artist }) => {
    const navigate = useNavigate();

    const handleClick = () => {
        navigate(`/artists/${artist.id}/events`);
    };

    return (
        <Card>
            <CardMedia
                component="img"
                height="200"
                image={artist.imageUrl}
                alt={artist.firstName}
            />
            <CardContent>
                <Typography variant="h6">
                    {artist.firstName} {artist.lastName}
                </Typography>
                <Typography variant="body2">
                    {artist.description}
                </Typography>
                <Button
                    sx={{
                        mt: 2,
                        backgroundColor: "white",
                        color: "black",
                        border: "1px solid black",
                        "&:hover": {
                            backgroundColor: "black",
                            color: "white"
                        }
                    }}
                    onClick={handleClick}
                >
                    View Details
                </Button>
            </CardContent>
        </Card>
    );
};

export default ArtistCard;
