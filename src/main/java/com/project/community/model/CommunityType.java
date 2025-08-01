package com.project.community.model;

public enum CommunityType {

        APARTMENTS("APARTMENTS"),
        CONDOS("CONDOS"),
        TOWNHOUSES("TOWNHOUSES"),
        SINGLE_FAMILY("SINGLE FAMILY");

        private String displayName;
        CommunityType(String displayName) {
                this.displayName = displayName;
        }

        public String getDisplayName() {
                return displayName;
        }

        @Override
        public String toString() {
                return displayName;
        }
}
