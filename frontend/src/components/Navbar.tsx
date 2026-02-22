import React from 'react';
import { AppBar, Toolbar, Typography, Button, Box } from "@mui/material";
import { Link } from "react-router-dom";

const Navbar: React.FC = () => {
    return (
        <AppBar
            position="fixed"
            sx={{ backgroundColor: "#424242" }}
        >
            <Toolbar>
                <Typography sx={{ flexGrow: 1 }}>
                   <h2>VibeCheck</h2>
                </Typography>

                <Button
                    component={Link}
                    to="/"
                    sx={{
                        backgroundColor: "white",
                        color: "black",
                        mx: 1,
                        "&:hover": {
                            backgroundColor: "black",
                            color: "white"
                        }
                    }}
                >
                    Events
                </Button>

                <Button
                    component={Link}
                    to="/artists"
                    sx={{
                        backgroundColor: "white",
                        color: "black",
                        mx: 1,
                        "&:hover": {
                            backgroundColor: "black",
                            color: "white"
                        }
                    }}
                >
                    Artists
                </Button>
            </Toolbar>
        </AppBar>
    );
};

export default Navbar;