package com.project3.elasticsearch.entity;

public class SearchResult {
        private Word entity;
        private float score;

        public SearchResult(Word entity, float score) {
            this.entity = entity;
            this.score = score;
        }

        public Word getEntity() {
            return entity;
        }

        public float getScore() {
            return score;
        }
}
